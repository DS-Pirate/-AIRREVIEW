package ds.pirate.backend.dto;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.airUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SaveDTO {
    private Long savid;
    private Long aid;
    private Long userid;
}
