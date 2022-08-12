package ds.pirate.backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SaveList extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savid;


    @ManyToOne(fetch = FetchType.LAZY)
    private ArticlesList articles;


    @ManyToOne(fetch = FetchType.LAZY)
    private airUser userid;

}
