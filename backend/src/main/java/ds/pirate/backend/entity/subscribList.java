package ds.pirate.backend.entity;

import lombok.*;
import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "userid")
public class subscribList extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sbid;
    private Long targetId;

    @ManyToOne(fetch = FetchType.LAZY)
    private airUser userid;
}
