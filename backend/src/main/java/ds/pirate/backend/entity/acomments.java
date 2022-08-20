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
    @JoinColumn(name = "articles_id", referencedColumnName = "aid")
    private ArticlesList articles;

    @ManyToOne
    @JoinColumn(name = "airuser", referencedColumnName = "userid")
    private airUser airuser;

    @Column
    private Long commentGroup;

    @Column
    private Long commnetDepth;

    @Column
    private Long commentSorts;

    @Column
    private Long commentContext;



    @Column
    @ColumnDefault("0") //default 0
    private Integer rate;




}
