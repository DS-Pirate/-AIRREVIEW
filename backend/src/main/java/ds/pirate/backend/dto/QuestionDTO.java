package ds.pirate.backend.dto;

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
    private Long userid;
    private String title;
    private String context;
    private boolean answered;
    private String answerContext;
}
