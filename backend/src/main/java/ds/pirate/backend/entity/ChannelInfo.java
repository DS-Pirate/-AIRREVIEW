package ds.pirate.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChannelInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chid;

    @Column
    private String chName;

    @Column
    private String chInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private airUser airUser;

}
