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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import ds.pirate.backend.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AirLogoutSuccessHandler implements LogoutSuccessHandler{

  @Override
  public void onLogoutSuccess(HttpServletRequest request, 
                              HttpServletResponse response, 
                              Authentication auth)
      throws IOException, ServletException {
    log.info("onLogoutSuccess");

    AuthMemberDTO dto = (AuthMemberDTO)auth.getPrincipal();
    List<String> roleNames = new ArrayList<>();
    dto.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
      @Override
      public void accept(GrantedAuthority t) {
        roleNames.add(t.getAuthority());
      }
    });
    String forward = "";
    System.out.println(">>>"+roleNames);
    if(roleNames.contains("ROLE_USER")) forward = "/";
    if(roleNames.contains("ROLE_MANAGER")) forward = "/member/login";
    if(roleNames.contains("ROLE_ADMIN")) forward = "/member/login";
    if(auth != null && auth.getDetails() != null){
      try {
        request.getSession().invalidate();
      } catch (Exception e) {e.printStackTrace();}
      response.setStatus(HttpServletResponse.SC_OK);
      response.sendRedirect(request.getContextPath()+forward);
    }
  }
}
