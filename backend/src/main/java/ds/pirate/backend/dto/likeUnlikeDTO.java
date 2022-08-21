package ds.pirate.backend.dto;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.airUser;
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
    private boolean likeUnlike;
    private ArticlesList articles;
    private airUser userid;
}
