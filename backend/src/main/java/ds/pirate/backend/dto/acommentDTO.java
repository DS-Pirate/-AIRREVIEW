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
public class acommentDTO {

    private Long cid;
    private ArticlesList articles; //aid
    private airUser airuser; // userid
    private Long commentGroup;
    private Long commnetDepth;
    private Long commentSorts;
    private Long commentContext;
    private Integer rate; //코멘트 좋아요싫어요
    private Integer articleRate; // 글 별점
}
