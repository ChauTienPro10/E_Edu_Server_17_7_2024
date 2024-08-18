package edu.member.student.mapper;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.entity.Student;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentCreationRequest requset); // chuyen doi tuong request ve Student
    StudentResponse toStudentResponse(Student entity); // chuyen doi tuong student ve doi tuong response de tra ve cho nguoi dung

}
