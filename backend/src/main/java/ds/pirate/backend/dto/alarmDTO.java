package ds.pirate.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class alarmDTO {
    private Long arid;
    private Long articleId;
    private Long commentId;
    private Long whoUser;
    private Long toUser;
    private boolean checked;

}
