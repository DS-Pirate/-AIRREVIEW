package ds.pirate.backend.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ds.pirate.backend.entity.acomments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class airUserDTO {
    private String passwd;
    private String email;
    private String airName;
    private LocalDateTime birthDay;
    private int gender;
    private boolean auth;
    private String recentArticles;
    private String q1;
    private String q2;
    private String q3;
    private String userIntro;
    private String userImgName;
    private String chName;
    private List<acomments> commentList;

    @Builder.Default
    private Set<String> roleSet = new HashSet<>();

}