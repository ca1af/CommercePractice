package com.example.commerce.auth.jwt;

import com.example.commerce.auth.dto.TokenResponse;
import com.example.commerce.user.dto.UserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret.key}")
    protected byte[] byteKey;
    /**
     * byteKey가 HMAC-SHA 알고리즘으로 암호화 된 키
     */
    protected Key key;

    @Value("${jwt.live.atk}")
    private Long atkTime;

    @Value("${jwt.live.rtk}")
    private Long rtkTime;

    static String Bearer = "Bearer ";
    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(this.byteKey);
    }

    public Authentication createAuthentication(UserDetails userDetails){
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String createToken(String email, Long tokenLive) {
        //String subjectStr = objectMapper.writeValueAsString(email);
        Claims claims = Jwts.claims()
                .setSubject(email);
        Date date = new Date();
        return Bearer + Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenLive))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public TokenResponse createTokensByLogin(UserResponse userResponse) {
        String email = userResponse.getEmail();
        String atk = createToken(email, atkTime);
        String rtk = createToken(email, rtkTime);
//        redisDao.setValues(email, rtk, Duration.ofMillis(rtkTime));
        return new TokenResponse(atk, rtk);
    }

    public String getSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
}
