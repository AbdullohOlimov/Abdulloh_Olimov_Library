package com.example.library.security;

import com.example.library.service.LibrarianService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    private final JwtFilter jwtFilter;
    private final LibrarianService librarianService;

    public SecurityConfig(JwtFilter jwtFilter, LibrarianService librarianService) {
        this.jwtFilter = jwtFilter;
        this.librarianService = librarianService;
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(librarianService);
        return provider;
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(provider())
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/librarian/token").permitAll()
                                .antMatchers("/books/allBooks").permitAll()
                                .antMatchers("/authors/getAll").permitAll()
                                .antMatchers("/student/rent-book").permitAll()
                                .antMatchers("/student/check/{id}").permitAll()
                                .antMatchers("/authors/get-books/{id}").permitAll()
                                .antMatchers("/library/swagger-ui.html").permitAll()
                                .antMatchers("/library/swagger-resources/**").permitAll()
                                .antMatchers("/library/swagger-ui/**").permitAll()
                                .antMatchers("/library/v2/api-docs").permitAll()
                                .antMatchers("/v3/**").permitAll()
                                .anyRequest().fullyAuthenticated()
                );
        return http.build();
    }

}

