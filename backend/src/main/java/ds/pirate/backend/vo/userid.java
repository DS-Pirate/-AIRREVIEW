package ds.pirate.backend.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class userid {
    Long userid;
    String cpasswd;
    String upasswd;
    String name;
    String email;
    String userintro;
    LocalDateTime birthDay;
}
