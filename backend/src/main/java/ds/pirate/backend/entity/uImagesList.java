package ds.pirate.backend.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class uImagesList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid; // pk

    @Column
    private String fileName;

    @Column
    private int idx;

    @ManyToOne
    @JoinColumn(name = "airuser", referencedColumnName = "userid")
    private airUser airuser;

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

}
