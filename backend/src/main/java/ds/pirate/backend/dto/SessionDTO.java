package ds.pirate.backend.dto;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SessionDTO {
    private Long userid;
    private String email;
    private String username;
    private String name;
    private String token;
    private String curl;
    private boolean fromSocial;
    private Map<String, Object> attr; //OAuth from Social
    //세션발급용
}