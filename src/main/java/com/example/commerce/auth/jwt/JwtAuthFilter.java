package com.example.commerce.auth.jwt;

import com.example.commerce.auth.userDetails.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");

        if (!validateToken(accessToken)) {
            throw new RuntimeException();
        }

        setAuthentication(request, accessToken);

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(HttpServletRequest request, String token) {
        try {
            String email = jwtProvider.getSubject(token);
            UserDetails userDetails = userDetailsImpl.loadUserByUsername(email);
            Authentication authentication = jwtProvider.createAuthentication(userDetails);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            request.setAttribute("exception", e.getMessage());
        }
    }

    public boolean validateToken(String token) {
        try {
            var encodeKey = Base64.getEncoder().encodeToString(jwtProvider.byteKey);
            Jwts.parserBuilder().setSigningKey(encodeKey).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {// 전: 권한 없다면 발생 , 후: JWT가 올바르게 구성되지 않았다면 발생
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {// JWT만료
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }
}
