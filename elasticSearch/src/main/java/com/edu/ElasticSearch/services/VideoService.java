package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Video;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.repository.VideoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class VideoService {

    VideoRepository videoRepository;

    public ApiResponse<Video>  newVideo(Video request) {
        if(request.getTitle().isEmpty()){
            return ApiResponse.<Video>builder()
                    .code(7003)
                    .message("tiêu đề không đuược bỏ trống")
                    .result(null)
                    .build();
        }
        if(videoRepository.findByLink(request.getLink()).isPresent()){
            return ApiResponse.<Video>builder()
                    .code(ErrorCode.ERR_VIDEO_EXISTED.getCode())
                    .message(ErrorCode.ERR_VIDEO_EXISTED.getMessage())
                    .result(null)
                    .build();
        }
        // Kiểm tra xem có video nào với title giống nhau không
       Optional <Video> existingVideoByTitle = videoRepository.findByTitle(request.getTitle());
       if(existingVideoByTitle.isPresent()){
           return ApiResponse.<Video>builder()
                   .code(ErrorCode.ERR_VIDEO_NAME_EXISTED.getCode())
                   .message(ErrorCode.ERR_VIDEO_NAME_EXISTED.getMessage())
                   .result(null)
                   .build();
       }
        else {
            // Kiểm tra xem có video nào với STT giống nhau không
            Optional<Video>  existingVideoByStt = videoRepository.findByStt(request.getStt());
            if (existingVideoByStt.isEmpty()) {
                // Nếu không có video nào tồn tại, lưu video mới
                return ApiResponse.<Video>builder()
                        .code(1000)
                        .message("OK")
                        .result(videoRepository.save(request))
                        .build();
            } else {
                // Cập nhật STT cho các video cùng course
                List<Video> videos = videoRepository.findByCourse(request.getCourse());
                for (Video video : videos) {
                    if (video.getStt() >= request.getStt()) {
                        video.setStt(video.getStt() + 1);
                        videoRepository.save(video);
                    }
                }
                return ApiResponse.<Video>builder()
                        .code(1000)
                        .message("OK")
                        .result(videoRepository.save(request))
                        .build();
            }
        }

    }

    public boolean removeVideo(String id) {
        // Tìm video theo ID
        Optional<Video> videoOptional = Optional.ofNullable(videoRepository.findById(id));
        if (videoOptional.isPresent()) {
            videoRepository.delete(videoOptional.get());
            return true;
        }
        return false;
    }

    public List<Video> getVideoByCourse(String nameCourse) {
        return videoRepository.findByCourseOrderBySttAsc(nameCourse);
    }
}
