//package ds.pirate.airreview.entity.boardsarticle;
//
//import lombok.*;
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
//public class tbHashTags {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long hid;
//    private String tagName;
//
//    @Column(name = "regdate", updatable = false)
//    private LocalDateTime regDate;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private tbArticles atid;
//
//
//
//}
