package ds.pirate.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HashTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hid;
    
    @Column(nullable = false)
    private String hashTagName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articles_id", referencedColumnName = "aid")
    private ArticlesList articles;
    

    //as mentioned about entity, set by manually
    public void updateArticles(ArticlesList list){
        this.articles = list;
    }
}
