package ds.pirate.backend.security.util;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
    private final String secretKey = "airreviewsecretkeyyyyyyyyyyyyyyyyyyyyyyyy";
    private final long expire = 60 * 24 * 30;

    public String generateToken(String email, Long userid) throws Exception {
        String result = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                .claim("sub", email)
                .claim("jti", userid)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        log.info(result);
        return result;
    }

    @SuppressWarnings("rawtypes")
    public Boolean validateAndExtract(String tokenStr, String userid) {
        Boolean checker = null;
        try {
            DefaultJws defaultjJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(tokenStr);
            
            DefaultClaims claims = (DefaultClaims) defaultjJws.getBody();
            String uid = claims.getId();
            if(Integer.parseInt(uid) == Integer.parseInt(userid)){
                checker = true;
            }else{
                checker = false;
            } 
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return checker;
    }

    @SuppressWarnings("rawtypes")
    public boolean validateAdmin(String userid, String tokenStr){
        Boolean checker = null;
        try {
            DefaultJws defaultJws = (DefaultJws) Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(tokenStr);
            DefaultClaims claims = (DefaultClaims) defaultJws.getBody();
            String uid = claims.getId();
            if(Integer.parseInt(uid) == Integer.parseInt(userid)){
                checker = true;
            }else{
                checker = false;
            } 

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return checker;
    }

}
