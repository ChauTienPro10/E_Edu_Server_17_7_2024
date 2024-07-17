package edu.member.student.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node("Student_profile")
public class Student {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;
    @Property("userId")
    String userId;
    String firstName;
    String lastName;
    LocalDate dob;
    String level;
    String phone;
    String email;
}
