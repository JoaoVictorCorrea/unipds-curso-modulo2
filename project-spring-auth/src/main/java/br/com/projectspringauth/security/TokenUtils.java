package br.com.projectspringauth.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class TokenUtils {

    public static Authentication decode(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if(token.equals("security123")){
                return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
            }
        }
        return null;
    }
}
