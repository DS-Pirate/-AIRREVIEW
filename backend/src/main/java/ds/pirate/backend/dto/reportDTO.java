package ds.pirate.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class reportDTO {
    private Long reid;
    private String reportContext;
    private Long articleid;
    private Long userid;
    
}
