package edu.app.gateway.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Management {
    long numOfTeacher;
    long numOfStudent;
    long numOfCourse;
    long numOfRegister;
    long numOfEnroll;
    Typeof_teacher typeofTeacher;
    TypesOfCourses typesOfCourses;


}
