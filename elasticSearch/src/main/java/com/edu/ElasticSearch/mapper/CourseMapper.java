package com.edu.ElasticSearch.mapper;

import com.edu.ElasticSearch.dto.request.CreateCourseRequest;
import com.edu.ElasticSearch.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    CreateCourseRequest toCrequest(Course course);

    Course toCourse(CreateCourseRequest courseDTO);


}
