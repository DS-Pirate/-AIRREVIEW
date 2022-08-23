package ds.pirate.backend.security.filter;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ds.pirate.backend.security.util.JWTUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
    private AntPathMatcher antPathMatcher;
    private String pattern;
    private JWTUtil jwtUtil;

    public ApiCheckFilter(String pattern, JWTUtil jwtUtil){
        antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(antPathMatcher.match(request.getContextPath()+pattern, request.getRequestURI())){
            log.info("API check filter......................."+request);
            boolean checkHeader = checkAuthHeader(request);
            log.info("checkHeader"+checkHeader);
            if(checkHeader){
                filterChain.doFilter(request, response);
                log.info("checkHeader: " + checkHeader);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json;charset=utf-8");
                JSONObject json = new JSONObject();
                json.put("code", "403");
                PrintWriter out = response.getWriter();
                out.print(json);
            }
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;
        String authHeader = request.getHeader("Authorization");
        log.info(authHeader);
        log.info("Authorization: " + authHeader);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            log.info("Authorization exist: " + authHeader);
            try {
                String email = jwtUtil.validateAndExtract(authHeader.substring(7));
                log.info(("validate result: " + email));
                checkResult = email.length() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return checkResult;
    }
}
