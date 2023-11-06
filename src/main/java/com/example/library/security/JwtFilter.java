package com.example.library.security;

import com.example.library.service.LibrarianService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    private final LibrarianService librarianService;
    public JwtFilter(JwtTokenUtil jwtTokenUtil, LibrarianService librarianService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.librarianService = librarianService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String username = null;
        if(token != null && token.startsWith("Bearer: ")){
            token = token.substring(7);
            try {
                username = jwtTokenUtil.getEmailFromJwt(token);
            }catch (IllegalArgumentException e){
                System.out.println("Not valid");
            }catch (ExpiredJwtException e){
                System.out.println("Expired token");
            }catch (Exception e){
                System.out.println("Something went wrong");
            }
            if (username != null && jwtTokenUtil.checkExpireDate(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = librarianService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}


