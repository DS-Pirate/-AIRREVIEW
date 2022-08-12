//package ds.pirate.airreview.entity.mychanel;
//
//import ds.pirate.airreview.entity.users.tbUserIcon;
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
//public class tbMyChan {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long cid;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private tbUsers uid;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private tbUserIcon upid;
//
//    private String chid;
//    private String chinfo;
//
//}
