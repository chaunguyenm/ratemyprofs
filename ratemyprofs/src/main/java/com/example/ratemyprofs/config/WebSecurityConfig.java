package com.example.ratemyprofs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.ratemyprofs.security.CustomPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .anyRequest().permitAll() // any files in /js/ and /img/ no authentication
//                .anyRequest().authenticated() // all requests need authentication
            )
            .formLogin((form) -> form //use login page to authenticate
                .loginPage("/login")
                .permitAll() 
            )
            .logout((logout) -> logout
                    .permitAll()
                    .logoutSuccessUrl("/")  ) // logout no authentication
            .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { // use bcrypt by default
        return new CustomPasswordEncoder();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("ROLE")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}
