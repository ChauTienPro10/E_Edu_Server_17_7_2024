package com.edu.ElasticSearch.services;

import com.edu.ElasticSearch.entity.Teacher;
import com.edu.ElasticSearch.repository.TeacherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TeacherService {
    TeacherRepository teacherRepository;

    public Teacher createNewTeacher(Teacher request){
        try{
            return teacherRepository.save(request);
        }
        catch (Exception e){
            log.info(e.toString());
            return null;
        }
    }


}
