package ds.pirate.backend.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"images", "cgroup"})
public class ArticlesList extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;

    @Column
    private String atitle;

    @Column
    private String context;

    @Column
    private Long alike;

    @Column
    private Long aunlike;

    @Column
    private Long reports;

    @Column
    private String hashTag;

    @Column
    @ColumnDefault("0") //default 0
    private Integer rate;

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImagesList> images = new ArrayList<>();

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<acomments> cgroup = new ArrayList<>();

}