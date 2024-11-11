package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.AddCommentResolveRequest;
import com.edu.ElasticSearch.dto.request.CreateResolveRequest;
import com.edu.ElasticSearch.dto.request.LikeResolveRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.dto.response.ResolveResponse;
import com.edu.ElasticSearch.entity.*;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.repository.CommentOfResolveRepository;
import com.edu.ElasticSearch.repository.LikeRepository;
import com.edu.ElasticSearch.repository.PracticeRepository;
import com.edu.ElasticSearch.repository.PracticeResolveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PracticeResolveService {
    @Autowired
    PracticeResolveRepository practiceResolveRepository;
    @Autowired
    PracticeRepository practiceRepository;

    @Autowired
    LikeRepository likeRepository;

    public ApiResponse<ResolveResponse> saveNewPracticeResolve(CreateResolveRequest request) {

        if (practiceRepository.findById(request.getPracticeId()).isEmpty()) {
            return ApiResponse.<ResolveResponse>builder()
                    .code(ErrorCode.ERR_PRACTICE_ID_NOT_EXIST.getCode())
                    .message(ErrorCode.ERR_PRACTICE_ID_NOT_EXIST.getMessage())
                    .build();
        }
        try {


            PracticeResolve newResolve = PracticeResolve.builder()
                    .likes(new ArrayList<Like>())
                    .practiceId(request.getPracticeId())
                    .observes(new ArrayList<ObserveOfResolve>())
                    .comment(new ArrayList<CommentOfResolve>())
                    .timestamp(LocalDateTime.now())
                    .studentEmail(request.getStudentEmail())
                    .content(request.getContent())
                    .build();
            practiceResolveRepository.save(newResolve);
            return ApiResponse.<ResolveResponse>builder()
                    .code(1000)
                    .message("OK")
                    .result(ResolveResponse.builder()
                            .id(newResolve.getId())
                            .timestamp(newResolve.getTimestamp())
                            .practiceId(newResolve.getPracticeId())
                            .likes(newResolve.getLikes())
                            .studentEmail(newResolve.getStudentEmail())
                            .observes(newResolve.getObserves())
                            .content(request.getContent())
                            .comment(newResolve.getComment())
                            .build())
                    .build();

        } catch (Exception e) {
            return ApiResponse.<ResolveResponse>builder()
                    .result(null)
                    .code(500)
                    .message("Error from server")
                    .build();
        }
    }

    public List<ResolveResponse> getAllPracticeResolve(String practiceId) {
        List<ResolveResponse> responses = new ArrayList<ResolveResponse>();
        List<PracticeResolve> practiceResolves = practiceResolveRepository.findAllByPracticeId(practiceId);
        for (PracticeResolve practiceResolve : practiceResolves) {

            responses.add(ResolveResponse.builder()
                    .id(practiceResolve.getId())
                    .likes(practiceResolve.getLikes())
                    .practiceId(practiceResolve.getPracticeId())
                    .observes(practiceResolve.getObserves())
                    .comment(practiceResolve.getComment())
                    .content(practiceResolve.getContent())
                    .studentEmail(practiceResolve.getStudentEmail())
                    .timestamp(practiceResolve.getTimestamp())
                    .build());
        }
        return responses;
    }

    public Like likeResolve(LikeResolveRequest request) {
        if (likeRepository.findByEmailAndPracticeResolveId(request.getEmail(), request.getResolveId()).isPresent()) {
            return null;
        }
        // Tìm PracticeResolve theo ID từ request
        Optional<PracticeResolve> practiceResolveOptional = practiceResolveRepository.findById(request.getResolveId());

        // Kiểm tra nếu không tìm thấy PracticeResolve thì trả về null
        if (practiceResolveOptional.isEmpty()) {
            return null;
        }

        PracticeResolve practiceResolve = practiceResolveOptional.get();

        // Tạo đối tượng Like mới
        Like like = Like.builder()
                .email(request.getEmail())
                .practiceResolveId(practiceResolve.getId())
                .build();

        // Lấy danh sách likes hiện tại từ PracticeResolve, nếu null thì tạo danh sách mới
        List<Like> likes = practiceResolve.getLikes();
        if (likes == null) {
            likes = new ArrayList<>();
        }

        // Thêm like mới vào danh sách
        likes.add(like);


        // Cập nhật lại danh sách likes trong PracticeResolve
        practiceResolve.setLikes(likes);


        // Lưu PracticeResolve với danh sách like mới
        practiceResolveRepository.save(practiceResolve);

        // Lưu đối tượng Like mới vào likeRepository và trả về
        return likeRepository.save(like);
    }


    @Autowired
    CommentOfResolveRepository commentOfResolveRepository;

    public CommentOfResolve addNewCmt(AddCommentResolveRequest request) {
        Optional<PracticeResolve> practiceResolve = practiceResolveRepository.findById(request.getResolveId());
        if (practiceResolve.isEmpty()) {
            return null;
        }
        CommentOfResolve newCmt = CommentOfResolve.builder()
                .content(request.getContent())
                .resolveId(request.getResolveId())
                .email(request.getEmail())
                .timestamp(LocalDateTime.now())
                .build();
        commentOfResolveRepository.save(newCmt);
        List<CommentOfResolve> cmts = practiceResolve.get().getComment();
        cmts.add(newCmt);
        practiceResolve.get().setComment(cmts);
        practiceResolveRepository.save(practiceResolve.get());
        return newCmt;
    }


}
