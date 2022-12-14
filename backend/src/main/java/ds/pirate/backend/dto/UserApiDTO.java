package ds.pirate.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserApiDTO {
    private Long userid; 
    private String passwd;
    private String eMail;
    private String airName;
    private int phone;
    private LocalDateTime birthDay;
    private int gender;
    private String auth;
    private String recentArticles;
    private String q1;
    private String q2;
    private String q3;
    private String userIntro;
}
