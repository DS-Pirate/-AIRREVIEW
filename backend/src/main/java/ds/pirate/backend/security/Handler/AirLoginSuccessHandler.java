package ds.pirate.backend.security.Handler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import ds.pirate.backend.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AirLoginSuccessHandler implements AuthenticationSuccessHandler {
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  private PasswordEncoder encoder;
  public AirLoginSuccessHandler(PasswordEncoder enc){
    encoder = enc;
  }
    
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info("onAuthenticationSuccess");
    AuthMemberDTO dto = (AuthMemberDTO)authentication.getPrincipal();
    boolean fromSocial = dto.isAuth();
    log.info("isFromSocial : " + fromSocial);
    boolean passResult = encoder.matches("1", dto.getPassword());
    log.info("fromSocial && passResult: " + (fromSocial && passResult));
    if(fromSocial && passResult) {
      redirectStrategy.sendRedirect(request, response, "/member/modify?from=social");
      return;
    } 
    List<String> roleNames = new ArrayList<>();
    dto.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
      @Override
      public void accept(GrantedAuthority t) {
        roleNames.add(t.getAuthority());
      }
    });
    String forward = "";
    System.out.println(">>>"+roleNames);
    if(roleNames.contains("ROLE_USER")) forward = "/sample/all";
    if(roleNames.contains("ROLE_MANAGER")) forward = "/sample/member";
    if(roleNames.contains("ROLE_ADMIN")) forward = "/sample/admin";
    redirectStrategy.sendRedirect(request, response, forward);
  }
}
