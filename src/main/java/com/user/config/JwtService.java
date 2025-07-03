package com.user.config;
import com.user.users.domain.UserDto;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="a8350c41c93f93730385d0bd47f6ecc4fafc9ca581f1addb3d562348a70e10ac";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
public <T> T extractClaim(String token, Function<Claims, T> claimsResolver ) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
}
public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
}


public String generateToken(Map<String, Object> extraClaims, UserDetails user) {
return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(user.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith( getSignInKey(),  SignatureAlgorithm.HS256)
        .compact();
}

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenValid(token);
    }
    private boolean isTokenValid(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return  extractClaim(token, Claims::getExpiration);
    }
}
