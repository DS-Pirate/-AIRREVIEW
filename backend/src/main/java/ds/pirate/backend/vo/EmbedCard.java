package ds.pirate.backend.vo;

import ds.pirate.backend.repository.ArticleRepository;
import lombok.Data;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

@Data
public class EmbedCard {

    private String airName;
    private Long aid;
    private String atitle;
    private String context;
    private LocalDateTime regDate;
    private boolean opend;
    private Integer articleRate;
    private Long likeCount;

    private String fileName;

    public EmbedCard(ArticleRepository.getEmbedCardsInformation em){
        this.airName = em.getAirName();
        this.aid = em.getAid();
        this.atitle = em.getAtitle();
        this.context = updateContextToString(em.getContext());
        this.regDate = em.getRegDate();
        this.opend = em.getOpend();
        this.articleRate = em.getArticleRate();
        this.likeCount = em.getLikeCount();
        this.fileName = em.getFileName();

    }

    public String updateContextToString(byte[] context){
        String result = new String(context, Charset.forName("utf-8"));
        return result;
    }

}
