package ds.pirate.backend.vo;

import java.time.LocalDateTime;

import ds.pirate.backend.repository.ArticleReportRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class settingReportList {
  private Long reid;
  private Byte[] context;
  private Long aid;
  private LocalDateTime regDate;
  private Long userid;
  private Integer pageNum;

  public settingReportList(ArticleReportRepository.getMySettingReportList em) {
    this.reid = em.getReid();
    this.context = em.getContext();
    this.aid = em.getAid();
    this.regDate = em.getRegDate();
  }
}
