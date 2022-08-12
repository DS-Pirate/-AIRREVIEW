package ds.pirate.backend.entity;

import lombok.*;

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

    @Column
    private String title;

    @Column
    private String context;

    @Column
    private boolean answered;

    @Column
    private String answerContext;


}
