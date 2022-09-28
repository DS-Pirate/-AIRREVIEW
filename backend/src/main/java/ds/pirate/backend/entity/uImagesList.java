package ds.pirate.backend.entity;


import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class uImagesList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid; 

    @Column
    private String fileName;

    @Column
    private int idx;

    @ManyToOne
    @JoinColumn(name = "airuser", referencedColumnName = "userid")
    private airUser airuser;

}
