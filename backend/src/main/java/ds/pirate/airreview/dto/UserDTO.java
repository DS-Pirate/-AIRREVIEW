package ds.pirate.airreview.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;

@Data
public class UserDTO {
    private String pw;
    private String email;
    private String name;
    private String phone;
    private LocalDateTime birthday;
    private String gender;
    private Boolean auth;
    private String q1;
    private String q2;
    private String q3;
    private String selfIntro;
}
