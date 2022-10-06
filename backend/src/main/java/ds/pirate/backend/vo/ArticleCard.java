package ds.pirate.backend.vo;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleCard {
    private String username;
    private String title;
    private String context;
    private Long opencount;
    private String filename;
    private LocalDateTime regdate;
    
    public ArticleCard(
        String username, 
        String title, byte[] context, Long opencount, String filename, LocalDateTime regdate){
        this.username = username;
        this.title = title;
        this.context = updateContextToString(context);
        this.opencount = opencount;
        this.regdate = regdate;
    }

    public String updateContextToString(byte[] context){
        String result = new String(context, Charset.forName("utf-8"));
        return result;
    }
}
