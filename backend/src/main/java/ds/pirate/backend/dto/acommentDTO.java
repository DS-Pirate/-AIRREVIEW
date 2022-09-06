package ds.pirate.backend.dto;




import java.time.LocalDateTime;

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
    private Long aid; //aid
    private Long userid; // userid
    private String email;
    private String userName;
    private String userImgName;
    private Long commentGroup;
    private Long commnetDepth;
    private Long commentSorts;
    private String commentContext;
    private Integer rate; //코멘트 좋아요싫어요
    private Integer articleRate; // 글 별점
    private LocalDateTime regDate;
    private int israted; //댓글상태넘겨주기용
}
