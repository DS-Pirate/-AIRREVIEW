package ds.pirate.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class alarm extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long arid;

    @ManyToOne
    private ArticlesList articleId;

    @ManyToOne
    private acomments commentId;

    @ManyToOne
    private airUser whoUser;

    @ManyToOne
    private airUser toUser;

    @Column
    private boolean checked;

}
