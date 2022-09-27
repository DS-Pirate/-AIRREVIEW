package ds.pirate.backend.vo;

import java.time.LocalDateTime;

import ds.pirate.backend.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class settingArticleList {
  private Long aid;
  private String atitle;
  private Byte[] context;
  private Integer favCount;
  private Integer saveCount;
  private Integer openCount;
  private LocalDateTime regDate;
  private Long userid;
  private Integer pageNum;

  public settingArticleList(ArticleRepository.getMySettingArticleList em) {
    this.aid = em.getAid();
    this.atitle = em.getAtitle();
    this.context = em.getContext();
    this.favCount = em.getFavCount();
    this.saveCount = em.getSaveCount();
    this.openCount = em.getOpenCount();
    this.regDate = em.getRegDate();
  }

}
