package ds.pirate.backend.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"airuser", "articles"})
public class acomments extends DateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @ManyToOne
    private ArticlesList articles;

    @ManyToOne
    private airUser airuser;

    @Column(nullable = false)
    private Long commentGroup;

    @Column(nullable = false)
    private Long commnetDepth;

    @Column(nullable = false)
    private Long commentSorts;

    @Column(nullable = false)
    private String commentContext;



    @Column
    @ColumnDefault("0") //default 0
    private Integer rate;

    @Column(nullable = false) // 글 평점용 컬럼
    @ColumnDefault("0")
    private Integer articleRate;




}
