package ds.pirate.backend.entity;


import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImagesList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid; // pk

    @Column
    private String fileName;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int idx;

    @ManyToOne
    @JoinColumn(name = "articles_id", referencedColumnName = "aid")
    private ArticlesList articles;


}
