package edu.member.student.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node("register")
public class RegisterCourse {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;
    String email;
    String course; // id course
    LocalDateTime time; // thoi gian dang ky
}
