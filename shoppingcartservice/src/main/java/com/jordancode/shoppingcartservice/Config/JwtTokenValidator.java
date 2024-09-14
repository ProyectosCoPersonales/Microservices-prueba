package com.jordancode.shoppingcartservice.Config;

import java.io.IOException;

import javax.crypto.SecretKey;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        if (jwt != null) {
            try {
                @SuppressWarnings("unchecked")
                Jws<Claims> claims = (Jws<Claims>) Jwts.parserBuilder().setSigningKey(JwtConstant.SECRET_KEY);
                if (!claims.getSignature().equals(JwtConstant.SECRET_KEY)) {
                    throw new BadCredentialsException("INVALID TOKEN...");
                }
            } catch (Exception e) {
                throw new BadCredentialsException("INVALID TOKEN...");
            }
        }
        filterChain.doFilter(request, response);
    }

}
