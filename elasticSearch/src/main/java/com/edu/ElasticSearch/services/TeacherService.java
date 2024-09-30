package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.dto.request.UserCreateRequest;
import com.edu.ElasticSearch.dto.response.ApiResponse;
import com.edu.ElasticSearch.entity.Teacher;
import com.edu.ElasticSearch.exception.ErrorCode;
import com.edu.ElasticSearch.exception.TeacherException;
import com.edu.ElasticSearch.repository.SubjectRepository;
import com.edu.ElasticSearch.repository.TeacherRepository;
import com.edu.ElasticSearch.repository.httpClients.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TeacherService {
    TeacherRepository teacherRepository;
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    SubjectRepository subjectRepository;
    IdentityClient identityClient;
    public ApiResponse<Teacher> createNewTeacher(Teacher teacher) {
        try {
            if(!isValidEmail(teacher.getEmail())){
                return ApiResponse.<Teacher>builder()
                        .code(TeacherException.TEACHER_EMAIL_INVALID.getCode())
                        .message(TeacherException.TEACHER_EMAIL_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            if(teacherRepository.findByEmail(teacher.getEmail()).isPresent()){
                return ApiResponse.<Teacher>builder()
                        .code(TeacherException.TEACHER_CREATE_EMAIL_EXISTED.getCode())
                        .message(TeacherException.TEACHER_CREATE_SUBJECT_CODE_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            if(subjectRepository.findByCode(teacher.getMajor()).isEmpty()){
                return ApiResponse.<Teacher>builder()
                        .code(TeacherException.TEACHER_CREATE_SUBJECT_CODE_INVALID.getCode())
                        .message(TeacherException.TEACHER_CREATE_SUBJECT_CODE_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            if(teacher.getLevel()<1 || teacher.getLevel()>4){
                return ApiResponse.<Teacher>builder()
                        .code(TeacherException.TEACHER_CREATE_LEVEL_INVALID.getCode())
                        .message(TeacherException.TEACHER_CREATE_LEVEL_INVALID.getMessage())
                        .result(null)
                        .build();
            }
            //luu vao database
            Teacher newTeacher= elasticsearchRestTemplate.save(teacher);
            UserCreateRequest userCreateRequest=UserCreateRequest.builder()
                    .memberId(newTeacher.getId())
                    .username(newTeacher.getEmail())
                    .password("teacher12345")
                    .build();
            /// tao moi tai khoan login cho teacher
            identityClient.createNewAccount(userCreateRequest);
            // Save the Teacher document to Elasticsearch
            return ApiResponse.<Teacher>builder()
                    .code(1000)
                    .message("OK")
                    .result(newTeacher)
                    .build();

        } catch (Exception e) {
            // Log detailed error
            System.err.println("Error creating new teacher: " + e.getMessage());
            return null;
        }
    }
    public ApiResponse<List<Teacher>> getAllTeacher(){
        try{
            return ApiResponse.<List<Teacher>>builder()
                    .code(1000)
                    .message("OK")
                    .result(teacherRepository.findAll())
                    .build();
        }
        catch (Exception e){
            return ApiResponse.<List<Teacher>>builder()
                    .code(400)
                    .message(e.toString())
                    .result(null)
                    .build();
        }
    }
    public ApiResponse<List<Teacher>> find_by_level(int level,String code){
        try{
            if(code.isEmpty()){
                return ApiResponse.<List<Teacher>>builder()
                        .code(1000)
                        .message("OK")
                        .result(teacherRepository.findByLevel(level))
                        .build();
            }
            if(level==0){
                return ApiResponse.<List<Teacher>>builder()
                        .code(1000)
                        .message("OK")
                        .result(teacherRepository.findByMajor(code))
                        .build();
            }
            return ApiResponse.<List<Teacher>>builder()
                    .code(1000)
                    .message("OK")
                    .result(teacherRepository.findByLevelAndMajor(level,code))
                    .build();

        }
        catch (Exception e){
            return ApiResponse.<List<Teacher>>builder()
                    .code(400)
                    .message(e.toString())
                    .result(null)
                    .build();
        }
    }

    public static boolean isValidEmail(String email) {// kuem tra emial hop le hay khong
        // Biểu thức chính quy cho email
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }
}
