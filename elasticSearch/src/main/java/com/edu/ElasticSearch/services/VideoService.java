package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.entity.Video;
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

    public Video newVideo(Video request) {
        // Kiểm tra xem có video nào với title giống nhau không
        Video existingVideoByTitle = videoRepository.findByTitle(request.getTitle());
        if (existingVideoByTitle == null) {
            // Kiểm tra xem có video nào với STT giống nhau không
            Video existingVideoByStt = videoRepository.findByStt(request.getStt());
            if (existingVideoByStt == null) {
                // Nếu không có video nào tồn tại, lưu video mới
                return videoRepository.save(request);
            } else {
                // Cập nhật STT cho các video cùng course
                List<Video> videos = videoRepository.findByCourse(request.getCourse());
                for (Video video : videos) {
                    if (video.getStt() >= request.getStt()) {
                        video.setStt(video.getStt() + 1);
                        videoRepository.save(video);
                    }
                }
                return videoRepository.save(request);
            }
        }
        return null;
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
