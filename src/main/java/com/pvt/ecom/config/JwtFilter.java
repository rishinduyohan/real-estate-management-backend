package com.pvt.ecom.config;

import com.pvt.ecom.service.impl.JWTService;
import com.pvt.ecom.service.impl.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (header!=null && header.startsWith("Bearer ")){
            token = header.substring(7);
            email = jwtService.extractEmail(token);
        }

        if (email !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = context.getBean(UserDetailService.class).loadUserByUsername(email);

            if (jwtService.validateToken(token,userDetails)) {
                UsernamePasswordAuthenticationToken authorizedToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authorizedToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authorizedToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
