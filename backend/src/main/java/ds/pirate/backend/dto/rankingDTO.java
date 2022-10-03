package ds.pirate.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class rankingDTO {
    private Long rkid;
    private String keyword;
    private Long searchCount;
    private LocalDateTime regdate;
    private LocalDateTime moddate;
}
