//package ds.pirate.airreview.entity.sav;
//
//import ds.pirate.airreview.entity.boardsarticle.tbArticles;
//import ds.pirate.airreview.entity.users.tbUsers;
//import lombok.*;
//
//import javax.persistence.*;
//
//
//@Entity
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
//public class tbSavBox {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long sboxid;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private tbUsers uid;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private tbArticles atid;
//}
//