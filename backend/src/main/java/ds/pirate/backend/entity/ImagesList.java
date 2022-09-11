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
    private Long iid; // pk

    @Column
    private String fileName;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int idx;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articles_id", referencedColumnName = "aid")
    private ArticlesList articles;


}
