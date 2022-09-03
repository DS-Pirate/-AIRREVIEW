package ds.pirate.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class likeUnlikeDTO {
    

    private Long favid;
    private Long aid;
    private Long userid;
}
