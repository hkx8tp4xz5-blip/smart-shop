package com.smart.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/", "/landing").permitAll()
                .requestMatchers("/product/add", "/product/edit/**", "/product/delete/**").hasRole("admin")
                .requestMatchers("/category/**", "/supplier/**").hasRole("admin")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/403")
            )
            .headers(headers -> headers
                .frameOptions(policy -> policy.sameOrigin())
                .cacheControl())

            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
