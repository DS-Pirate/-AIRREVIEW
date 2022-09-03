package ds.pirate.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class acommentRateDTO {
    private int updown;
    private Long cid;
    private Long userid;
    
    
}
