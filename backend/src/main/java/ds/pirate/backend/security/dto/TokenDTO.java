package ds.pirate.backend.security.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TokenDTO {
    private Long userid;
    private String email;
    private String username;
    private String name;
    private String token;
    private boolean auth;
}