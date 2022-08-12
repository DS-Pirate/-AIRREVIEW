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
public class tbSubList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slid;
    private Long targetId;
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;


    @ManyToOne(fetch = FetchType.LAZY)
    private tbUsers uid;
}
