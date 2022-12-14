package ds.pirate.backend.entity;


import javax.persistence.*;

import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImagesList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    @Column
    private String fileName;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articles_id", referencedColumnName = "aid")
    private ArticlesList articles;


}
