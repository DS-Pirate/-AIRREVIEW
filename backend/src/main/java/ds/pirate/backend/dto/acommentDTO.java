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
    private Long aid; 
    private Long userid; 
    private String email;
    private String userName;
    private String userImgName;
    private Long commentGroup;
    private Long commnetDepth;
    private Long commentSorts;
    private String commentContext;
    private Integer rate; 
    private Integer articleRate; 
    private LocalDateTime regDate;
    private int israted; 
}
