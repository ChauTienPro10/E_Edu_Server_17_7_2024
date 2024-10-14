package edu.member.student.service;

import edu.member.student.dto.response.ApiResponse;
import edu.member.student.dto.response.Management;
import edu.member.student.repository.RegisterRepository;
import edu.member.student.repository.StudentRepository;
import edu.member.student.repository.httpClients.CourseClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ManagementService {
    @Autowired
    CourseClient courseClient;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RegisterRepository registerRepository;
    public ApiResponse<Management> getInforManagement(){
        log.info("getting infor");
        try{
            Management teacherAndCourse= courseClient.getNumOfTeacherAndCourses();
            return ApiResponse.<Management>builder()
                    .code(1000)
                    .message("OK")
                    .result(Management.builder()
                            .numOfStudent(studentRepository.count())
                            .numOfRegister(registerRepository.count())
                            .numOfTeacher(teacherAndCourse.getNumOfTeacher())
                            .numOfCourse(teacherAndCourse.getNumOfCourse())
                            .typeofTeacher(teacherAndCourse.getTypeofTeacher())
                            .typesOfCourses(teacherAndCourse.getTypesOfCourses())
                            .numOfEnroll(registerRepository.countUniqueStudents())
                            .build())
                    .build();
        }
        catch (Exception e){
            return ApiResponse.<Management>builder()
                    .code(999)
                    .message("Lỗi truy xuất thong tin")
                    .build();
        }
    }
}
