package edu.member.student.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Node("my_discount")
public class MyDiscount {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;

    String discountcode;
    String student;
    boolean status;
}
