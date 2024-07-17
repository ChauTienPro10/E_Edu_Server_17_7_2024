package edu.member.student.service;

import edu.member.student.dto.request.StudentCreationRequest;
import edu.member.student.dto.response.StudentResponse;
import edu.member.student.entity.Student;
import edu.member.student.mapper.StudentMapper;
import edu.member.student.mapper.StudentMapperImpl;
import edu.member.student.repository.StudentRepository;
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
public class StudentService {

    StudentRepository studentRepository;
    StudentMapper studentMapper;

    public StudentResponse newStudent(StudentCreationRequest request){
        Student newStd=studentMapper.toStudent(request);// chuyen yeu cau ve student
        newStd=studentRepository.save(newStd);// luu student
        return studentMapper.toStudentResponse(newStd); // tra ve
    }
}
