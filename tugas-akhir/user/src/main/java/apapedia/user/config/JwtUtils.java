package apapedia.user.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import apapedia.user.model.UserApapedia;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    //public static final String jwtSecret = System.getenv("JWT_SECRET");
    //public static final String jwtExpirationMs = System.getenv("JWT_EXPIRATION_MS");
    @Value("${user.app.jwtSecret}")
    private String jwtSecret;

    @Value("${user.app.jwtExpirationMs}")
    private String jwtExpirationMs;

    public String generateJwtToken(UserApapedia user){
        System.out.println(jwtSecret);
        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getIdUser())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS512, "OI4OVNONVONI2H3R238R92381983R919")
                .compact();
        System.out.println(jwt);
        return jwt;
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey("OI4OVNONVONI2H3R238R92381983R919").parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey("OI4OVNONVONI2H3R238R92381983R919").parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error(("Invalid JWT Signature: {}"), e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error(("Invalid JWT Signature: {}"), e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error(("JWT token is expired: {}"), e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error(("JWT token is unsupported: {}"), e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(("JWT claims string is emptyyy: {}"), e.getMessage());
        }
        return false;
    }
}
