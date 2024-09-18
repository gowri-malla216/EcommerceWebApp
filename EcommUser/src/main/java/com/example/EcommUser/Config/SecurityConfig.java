package com.example.EcommUser.Config;

import com.example.EcommUser.Helpers.JwtUtil;
import com.example.EcommUser.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private JwtAuthEntryPoint authEntryPoint;


   @Bean
   public SecurityFilterChain customFilterChain(HttpSecurity http, HttpSession httpSession) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable);
       http.exceptionHandling((exception)-> exception.authenticationEntryPoint(authEntryPoint).accessDeniedPage("/error/access-denied"));
       http.sessionManagement((session)-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
       http
               .authorizeHttpRequests(request -> {
//                   request.requestMatchers(HttpMethod.GET).authenticated();
//                   request.requestMatchers(HttpMethod.PUT).authenticated();
                   request.requestMatchers("/api/auth/register","/api/auth/login","/api/auth/validateToken").permitAll();
                   request.anyRequest().authenticated();
               });
//       http.formLogin(fL -> fL.loginPage("/login")
//               .usernameParameter("email").permitAll()
//               .defaultSuccessUrl("/", true)
//               .failureUrl("/login-error"));
       http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
       return http.build();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
       return new JwtAuthFilter();
    }
}
