package ds.pirate.backend.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class likeUnlikeList extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favid;

    @Column
    private boolean likeUnlike;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArticlesList articles;


    @ManyToOne(fetch = FetchType.LAZY)
    private airUser userid;

}
