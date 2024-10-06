package com.edu.ElasticSearch.controller;

import com.edu.ElasticSearch.dto.request.RemoveInforRequest;
import com.edu.ElasticSearch.dto.request.RemoveVideoRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Course;
import com.edu.ElasticSearch.entity.Video;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.repository.CourseRepository;
import com.edu.ElasticSearch.repository.TeacherRepository;
import com.edu.ElasticSearch.repository.VideoRepository;
import com.edu.ElasticSearch.services.JwtUtil;
import com.edu.ElasticSearch.services.VideoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoControler {
    JwtUtil jwtUtil;
    VideoService videoService;

    JwtDecoder jwtDecoder;
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/new")
    public ApiResponse<Video> createVideo(@RequestBody Video request,@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        Jwt jwt=jwtDecoder.decode(token);
        String email = jwtUtil.extractUsername(jwt);

        return videoService.newVideo(request,email);
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
        return videoService.getVideoByCourse(idcourse);
    }


}
