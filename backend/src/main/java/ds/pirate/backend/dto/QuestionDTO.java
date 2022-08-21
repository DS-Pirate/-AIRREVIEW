package ds.pirate.backend.dto;

import ds.pirate.backend.entity.airUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private Long qid;
    private airUser userid;
    private String title;
    private String context;
    private boolean answered;
    private String answerContext;
}
