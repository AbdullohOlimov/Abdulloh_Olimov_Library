package com.example.library.security;

import com.example.library.service.LibrarianService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String username = null;
        // Clear previous authentication
        SecurityContextHolder.getContext().setAuthentication(null);
        if(token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
            try {
                username = jwtTokenUtil.getEmailFromJwt(token);
            }
            catch (IllegalArgumentException e){
                System.out.println("Unable to get JWT Token");
            }
            catch (ExpiredJwtException e){
                System.out.println("JWT Token has expired");
            }
            catch (Exception e){
                System.out.println("Exception: " + e.getMessage());
            }

            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            if (username != null && jwtTokenUtil.checkExpireDate(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = librarianService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/swagger") || path.startsWith("/v2/api-docs") ||
        path.startsWith("/swagger-resources");
    }
}


