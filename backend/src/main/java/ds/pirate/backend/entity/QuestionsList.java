package ds.pirate.backend.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "userid")
public class QuestionsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qid;

    @ManyToOne(fetch = FetchType.LAZY)
    private airUser userid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String context;

    @ColumnDefault("0")
    private boolean answered;

    @Column
    private String answerContext;


}
