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

    @Column(nullable = false)
    private String atitle;

    @Column(nullable = false)
    private String context;

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImagesList> images = new ArrayList<>();

    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<acomments> cgroup = new ArrayList<>();

}