package ds.pirate.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ds.pirate.backend.security.Handler.AirAccessDeniedHandler;
import ds.pirate.backend.security.Handler.AirLoginSuccessHandler;
import ds.pirate.backend.security.Handler.AirLogoutSuccessHandler;
import ds.pirate.backend.security.Handler.ApiLoginFailHandler;
import ds.pirate.backend.security.filter.ApiCheckFilter;
import ds.pirate.backend.security.filter.ApiLoginFilter;
import ds.pirate.backend.security.util.JWTUtil;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
// By WebSecurityConfigurerAdapter is deprecated, change to Bean type as suggested on spring boot blog site
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception{
        http.csrf().disable();
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public ApiLoginFilter apiLoginFilter(AuthenticationManager authenticationManager) throws Exception {
    
      ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/member/login", jwtUtil());
      apiLoginFilter.setAuthenticationManager(authenticationManager);
      apiLoginFilter.setAuthenticationSuccessHandler(successHandler());
      apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
      return apiLoginFilter;
    }

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil();
    }

    @Bean
    public ApiCheckFilter apiCheckFilter(){
        return new ApiCheckFilter("/api/**/*", jwtUtil());
    }

    
    @Bean
    public AirLoginSuccessHandler successHandler() {
        return new AirLoginSuccessHandler(passwordEncoder());
    }

    @Bean
    public AirLogoutSuccessHandler logoutSuccessHandler() {
        return new AirLogoutSuccessHandler();
    }

    @Bean
    public AirAccessDeniedHandler accessDeniedHandler(){
      return new AirAccessDeniedHandler();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}





