package ds.pirate.airreview.entity.users;


import ds.pirate.airreview.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class tbUsers extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long uid;

    private String email;
    private String pw;
    private String name;
    private String phone;
    private LocalDateTime birthday;
    private String gender;
    private Boolean auth;
    private String q1;
    private String q2;
    private String q3;
    private String selfIntro;

    

}
