package ds.pirate.backend.dto;

import ds.pirate.backend.entity.ArticlesList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HashTagDTO {
    private Long hid;
    private String hashTagName;
    private ArticlesList articles;
}
