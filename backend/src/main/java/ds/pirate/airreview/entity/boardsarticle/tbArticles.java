package ds.pirate.airreview.entity.boardsarticle;

import ds.pirate.airreview.entity.users.tbUsers;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class tbArticles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tbArticleId;

    @OneToOne(fetch = FetchType.LAZY)
    private tbUsers uid;

    //comment-cgroup
    //tagid
    //report
    private String title;
    private String context;
    private Long like;
    private Long unlike;












}
