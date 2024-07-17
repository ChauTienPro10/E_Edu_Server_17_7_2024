package edu.member.student.mapper;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.entity.Student;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class StudentMapperImpl implements StudentMapper{
    @Override
    public Student toStudent(StudentCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Student.StudentBuilder student = Student.builder();

        student.userId( request.getUserId() );
        student.firstName( request.getFirstName() );
        student.lastName( request.getLastName() );
        student.dob( request.getDob() );
        student.level( request.getLevel() );
        student.phone(request.getPhone());
        student.email(request.getEmail());
        return student.build();
    }

    @Override
    public StudentResponse toStudentResponse(Student entity) {
        StudentResponse.StudentResponseBuilder studentResponseBuilder = StudentResponse.builder();// buold mot doi tuong student response
        studentResponseBuilder.firstName(entity.getFirstName());
        studentResponseBuilder.lastName(entity.getLastName());
        studentResponseBuilder.dob(entity.getDob());
        studentResponseBuilder.email(entity.getEmail());
        studentResponseBuilder.phone(entity.getPhone());
        return studentResponseBuilder.build();
    }
}
