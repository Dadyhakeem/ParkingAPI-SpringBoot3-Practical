package dev.hakeem.parkingapi_springboot3_practical.jwt;
import io.jsonwebtoken.Claims;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;



import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 30;

    private  JwtUtils(){

    }
    private static Key generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).minusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return  Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken (String username,String role){
        Date issuedAt = new Date();
        Date limit = toExpireDate(issuedAt);

        String token = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(limit)
                .signWith(generateKey(),SignatureAlgorithm.HS256)
                .claim("role",role)
                .compact();
        return  new JwtToken(token);
    }

    private static Claims getClaimsFromToken(String token) {
        try {
            if (token != null && token.startsWith(JWT_BEARER)) {
                token = token.substring(JWT_BEARER.length());
                return Jwts.parserBuilder()
                        .setSigningKey(generateKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
            } else {
                log.warn("Token inválido ou mal formatado: {}", token);
            }
        } catch (JwtException ex) {
            log.error("Erro ao analisar token JWT: {}", ex.getMessage());
        }
        return null;
    }


    public static String getUsernameFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(refactorToken(token))
                    .getBody();

            // Verifica se o token não está expirado
            Date expiration = claims.getExpiration();
            if (expiration != null && expiration.after(new Date())) {
                // Token válido se a data de expiração for posterior à data atual
                return true;
            } else {
                log.warn("Token expirado");
                return false;
            }
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("Token inválido: " + ex.getMessage());
        }
        return false;
    }


    private static String refactorToken (String token){
        if (token.contains(JWT_BEARER)){
            return  token.substring(JWT_BEARER.length());
        }
        return  token;
    }


}
