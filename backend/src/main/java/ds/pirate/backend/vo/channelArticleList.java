package ds.pirate.backend.vo;

import java.time.LocalDateTime;

import ds.pirate.backend.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class channelArticleList {
    private Long aid;
    private String atitle;
    private Integer favCount;
    private Integer saveCount;
    private Integer openCount;
    private LocalDateTime regDate;
    private Long userid;
    private Integer pageNum;

    public channelArticleList(ArticleRepository.getMyChannelArticleList em){
        this.aid = em.getAid();
        this.atitle = em.getAtitle();
        this.favCount = em.getFavCount();
        this.saveCount = em.getSaveCount();
        this.openCount = em.getOpenCount();
        this.regDate = em.getRegDate();

    }
}
