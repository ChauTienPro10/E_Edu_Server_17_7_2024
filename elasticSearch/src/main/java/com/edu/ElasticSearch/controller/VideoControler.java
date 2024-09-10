package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.RemoveInforRequest;
import com.edu.ElasticSearch.dto.request.RemoveVideoRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.entity.Video;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.repository.VideoRepository;
import com.edu.ElasticSearch.services.VideoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoControler {
    VideoService videoService;
    @PostMapping("/new")
    public ApiResponse<Video> createVideo(@RequestBody Video request){
        try{
            Video video =videoService.new_video(request);
            if(video==null){
                return ApiResponse.<Video>builder()
                        .code(ErrorCode.ERR_SAME_NAME_VIDEO.getCode())
                        .message(ErrorCode.ERR_SAME_NAME_VIDEO.getMessage())
                        .build();
            }
            return ApiResponse.<Video>builder() // tao Apiresponse tra ve
                    .code(1000)  // Success code (default)
                    .message("add video successfully")
                    .result(video)
                    .build();
        }
        catch (Exception e){
            log.info("error",e.toString());
            return ApiResponse.<Video>builder()
                    .code(ErrorCode.ERR_ADD_VIDEO.getCode())
                    .message(ErrorCode.ERR_ADD_VIDEO.getMessage())
                    .build();
        }
    }
    @PostMapping("/remove")
    public ApiResponse<Boolean> remove(@RequestBody RemoveVideoRequest request){
        boolean result= videoService.removeVideo(request.getVideoId());
        if(!result){
            return ApiResponse.<Boolean>builder()
                    .code(ErrorCode.ERR_REMOVE_VIDEDO.getCode())
                    .message(ErrorCode.ERR_REMOVE_VIDEDO.getMessage())
                    .build();
        }
        else{
            return ApiResponse.<Boolean>builder() // tao Apiresponse tra ve
                    .code(1000)  // Success code (default)
                    .message("remove video successfully")
                    .result(result)
                    .build();
        }
    }

    @GetMapping("/getListVideo")
    public List<Video> getListVideo(@RequestParam String idcourse){
        return videoService.getVidedoByCourse(idcourse);
    }


}
