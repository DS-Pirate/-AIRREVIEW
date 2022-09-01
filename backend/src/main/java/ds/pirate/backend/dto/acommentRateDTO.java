package ds.pirate.backend.dto;

import java.time.LocalDateTime;

import ds.pirate.backend.entity.acomments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class acommentRateDTO {
    private Long crid;
    private Integer rate;
    private acomments cid;
    private LocalDateTime regDate;
    
    
}
