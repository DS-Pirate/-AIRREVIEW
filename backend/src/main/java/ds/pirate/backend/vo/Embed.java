package ds.pirate.backend.vo;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;


import ds.pirate.backend.repository.ArticleRepository;
import lombok.Data;


@Data
public class Embed {

    private LocalDateTime regdate;
    private String title;
    private String context;
    private String author;
    private Long favcount;
    private Double avgrate;
    private List<String> imageList;

    public Embed(ArticleRepository.getEmbedInformation em){
        this.regdate = em.getRegdate();
        this.title = em.getTitle();
        this.context = updateContextToString(em.getContext());
        this.author = em.getAuthor();
        this.favcount = em.getFavcount();
        this.avgrate = em.getAvgrate();
        this.imageList = em.getImageList();

    }
    public String updateContextToString(byte[] context){
        String result = new String(context, Charset.forName("utf-8"));
        return result;
    }
}
