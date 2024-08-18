package edu.member.student.dto.request;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCreationRequest {
    String fullname;
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    String phone;
    @Email(message = "Email should be valid")
    String email;

    @Size(min = 7, max = 14, message = "Password must be between 7 and 14 characters long")
    String password;


    public String fullnameUper(String _fullname) { // overwrite ham khoi tao
        if (_fullname != null && !_fullname.isEmpty()) {
            return capitalizeFullname(_fullname);
        }
        else return this.fullname;
    }
    private String capitalizeFullname(String fullname) {// ham chuyen tat ca cac chu cai dau moi tu ve in hoa
        String[] words = fullname.split("\\s+");
        StringBuilder capitalizedFullname = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedFullname.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return capitalizedFullname.toString().trim();
    }
}
