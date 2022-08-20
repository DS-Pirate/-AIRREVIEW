package ds.pirate.backend.security.dto;


import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.*;

@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {
    private String email;
    private String password;
    private String name;
    private boolean auth;
    private Map<String, Object> attr; 

    public AuthMemberDTO(String username, String password,
                             boolean fromSocial, Collection<? extends GrantedAuthority> authorities,
                             Map<String, Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    public AuthMemberDTO(
            String username, String password, boolean auth,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        email = username;
        this.password = password;
        this.auth = auth;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attr;
    }
}
