package com.mcb.billing.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("pransh")
                .password("pransh")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests(auth -> auth
                        .requestMatchers("/api/users/**").authenticated() // Requires authentication for /api/users
                        .requestMatchers("/message").permitAll() // Allows access to /message without authentication
                        .anyRequest().denyAll() // Deny any other requests
                )
                .csrf().disable()
                .cors().disable()
                .httpBasic(); // Use HTTP Basic authentication

        return http.build();
    }


//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf-> csrf.disable())
//                .cors(cors-> cors.disable())
//                .authorizeHttpRequests(auth->
//                        auth.requestMatchers("/api/users/**").authenticated()
//                        .requestMatchers("/auth/login").permitAll().anyRequest().authenticated());
//
//
//
//        return http.build();
//    }


}