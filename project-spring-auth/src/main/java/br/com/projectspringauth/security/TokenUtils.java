package br.com.projectspringauth.security;

import br.com.projectspringauth.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtils {

    public static final String EMITTER = "br.com.projectspringauth";
    public static final long EXPIRATION = 60 * 60 * 1000; // 1 hour in milliseconds
    public static final String SECRET_KEY = "012345678901234567890123456789012";

    public static MyToken encode(User user) {
        try {
            Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            String jwtToken = Jwts.builder()
                                  .subject(user.getUsername())
                                  .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                                  .issuer(EMITTER)
                                  .signWith(key)
                                  .compact();
            return new MyToken(jwtToken);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Authentication decode(HttpServletRequest request) {
        try {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                JwtParser parser = Jwts.parser().verifyWith(key).build();
                Claims claims = parser.parseSignedClaims(token).getPayload();

                String subject = claims.getSubject();
                String issuer = claims.getIssuer();
                Date expiration = claims.getExpiration();

                if(issuer.equals(EMITTER) && !subject.isEmpty() && expiration.after(new Date())) {
                    return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
