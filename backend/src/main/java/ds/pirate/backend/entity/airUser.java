package ds.pirate.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class airUser  extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column
    private String passwd;

    @Column
    private String eMail;

    @Column
    private String airName;

    @Column
    private int phone;

    @Column
    private LocalDateTime birthDay;

    @Column
    private int gender;

    @Column
    private String auth;

    @Column
    private String recentArticles;

    @Column
    private String q1;
    @Column
    private String q2;
    @Column
    private String q3;
    @Column
    private String userIntro;



    @OneToMany(mappedBy = "airuser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<uImagesList> userImg = new ArrayList<>();

    @OneToMany(mappedBy = "airuser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<acomments> commentList = new ArrayList<>();



}
