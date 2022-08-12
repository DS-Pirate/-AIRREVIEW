package ds.pirate.airreview.entity.users;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class tbUserIcon {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long upid;

    private String upname;
    private String filepath;

    @ManyToOne(fetch = FetchType.LAZY)
    private tbUsers uid;

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regdate;

}
