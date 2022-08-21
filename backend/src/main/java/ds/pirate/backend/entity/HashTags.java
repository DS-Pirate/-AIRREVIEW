package ds.pirate.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "articles_id", referencedColumnName = "aid")
    private ArticlesList articles;
    
}
