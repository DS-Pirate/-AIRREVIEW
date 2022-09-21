package ds.pirate.backend.security.dto;


import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.*;

@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {
    private Long userid;
    private String email;
    private String password;
    private String name;
    private boolean auth;

    public AuthMemberDTO(String username, String password, Long userid, boolean auth, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userid = userid;
        email = username;
        this.password = password;
        this.auth = auth;
    }


}
