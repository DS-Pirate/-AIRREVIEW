//package ds.pirate.airreview.entity.boardsarticle;
//
//import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//
//@Entity
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
//public class tbThumbnails {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long tid;
//    private String tname;
//    private String filePath;
//    @CreatedDate
//    @Column(name = "regdate", updatable = false)
//    private LocalDateTime regDate;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private tbArticles atid;
//
//}
