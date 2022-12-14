package ds.pirate.backend.security.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletInputStream;

import ds.pirate.backend.security.dto.AuthMemberDTO;

import ds.pirate.backend.security.dto.TokenDTO;
import ds.pirate.backend.security.util.JWTUtil;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {
    private JWTUtil jwtUtil;

    public ApiLoginFilter(String defaultFilterProcessUrl, JWTUtil jwtUtil) {
        super(defaultFilterProcessUrl);
        this.jwtUtil = jwtUtil;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        log.info("ApiLoginFilter........ attemptAuthentication");
        log.info("request.getRequestURI():" + request.getRequestURI());

        ServletInputStream inputStream = request.getInputStream();

        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("msgBody:: " + msgBody);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) parser.parse(msgBody);
            log.info(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String email = jsonObject.get("email").toString();
        String pw = jsonObject.get("passwd").toString();
        log.info("email: " + email + "/pw: " + pw);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pw);
        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) {
        log.info("successfulAuthentication... authResult:" + authResult.getPrincipal());
        String email = ((AuthMemberDTO) authResult.getPrincipal()).getEmail();
        Long userid = ((AuthMemberDTO) authResult.getPrincipal()).getUserid();
        Boolean auth = ((AuthMemberDTO) authResult.getPrincipal()).isAuth();
        String token = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            token = "Bearer " + jwtUtil.generateToken(email, userid);
            TokenDTO tokenDTO = AuthToSessionDTO((AuthMemberDTO) authResult.getPrincipal(), token, auth);
            String res = mapper.writeValueAsString(tokenDTO);
            response.setContentType("application/json;charset=utf-8");
            response.getOutputStream().write(res.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private TokenDTO AuthToSessionDTO(AuthMemberDTO dto,
                                        String token, boolean auth) {
        TokenDTO tokenDTO = TokenDTO.builder()
                .userid(dto.getUserid())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .name(dto.getName())
                .token(token)
                .auth(auth)
                .build();
        return tokenDTO;
    }

}
