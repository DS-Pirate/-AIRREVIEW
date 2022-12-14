package ds.pirate.backend.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"roleSet", "commentList"})
public class airUser  extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String eMail;

    @Column(nullable = false)
    private String airName;

    @Column(nullable = false)
    private LocalDateTime birthDay;

    @Column(nullable = false)
    private int gender;

    @Column
    private boolean auth;

    @Column
    private String recentArticles;

    @Column(nullable = false)
    private String q1;
    @Column(nullable = false)
    private String q2;
    @Column(nullable = false)
    private String q3;

    @Column
    private String userIntro;

    @Column
    private String chName;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "airuser", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<uImagesList> userImg = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "airuser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<acomments> commentList = new ArrayList<>();



}
