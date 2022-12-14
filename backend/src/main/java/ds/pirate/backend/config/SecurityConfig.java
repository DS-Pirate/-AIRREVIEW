package ds.pirate.backend.config;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

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

import com.querydsl.jpa.impl.JPAQueryFactory;

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
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }

}



