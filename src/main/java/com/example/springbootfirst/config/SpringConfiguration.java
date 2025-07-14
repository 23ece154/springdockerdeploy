//package com.example.springbootfirst.config;
//
//import com.example.springbootfirst.services.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableMethodSecurity
//public class SpringConfiguration {
//
//  @Autowired
//  CustomUserDetailsService customUserDetailsService;
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                    .requestMatchers("/api/auth/**").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .httpBasic(Customizer.withDefaults());
//    return http.build();
//  }
//
//  @Bean
//  UserDetailsService userDetails(){
//    UserDetails admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder().encode("admin"))
//            .roles("ADMIN")
//            .build();
//    UserDetails ksp = User.builder()
//            .username("ksp")
//            .password(passwordEncoder().encode("ksp@123"))
//            .roles("USER")
//            .build();
//    return new InMemoryUserDetailsManager(admin,ksp);
//  }
//}

package com.example.springbootfirst.config;

import com.example.springbootfirst.jwt.JwtAuthenticationFilter;
import com.example.springbootfirst.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SpringConfiguration {

  @Autowired
  CustomUserDetailsService customUserDetailsService;

  @Autowired
  JwtAuthenticationFilter jwtAuthenticationFilter;


  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf((csrf)->csrf.disable())
            .authorizeHttpRequests(auth-> {
              auth.requestMatchers("/api/auth/**").permitAll();
              auth.anyRequest().authenticated();
            })
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
          AuthenticationConfiguration configuration)
          throws Exception {
    return configuration.getAuthenticationManager();

  }


}