package ds.pirate.backend.vo;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleCard {
    private Long userid;
    private String title;
    private String context;
    private Long opencount;
    private LocalDateTime regdate;
    

    @QueryProjection
    public ArticleCard(Long userid, String title, String context, Long opencount, LocalDateTime regdate){
    this.userid = userid;
    this.title = title;
    this.context = context;
    this.opencount = opencount;
    this.regdate = regdate;
    }
}
