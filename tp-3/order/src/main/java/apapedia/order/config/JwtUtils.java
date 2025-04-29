// package apapedia.order.config;

// import java.util.Date;
// import java.util.concurrent.TimeUnit;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.MalformedJwtException;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.SignatureException;
// import io.jsonwebtoken.UnsupportedJwtException;
// import org.springframework.beans.factory.annotation.Value;

// @Component
// public class JwtUtils {
//     private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//     //public static final String jwtSecret = System.getenv("JWT_SECRET");
//     //public static final String jwtExpirationMs = System.getenv("JWT_EXPIRATION_MS");
//     @Value("${user.app.jwtSecret}")
//     private String jwtSecret;

//     @Value("${user.app.jwtExpirationMs}")
//     private String jwtExpirationMs;

//     public String getUserNameFromJwtToken(String token){
//         return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//     }

//     public boolean validateJwtToken(String authToken){
//         try{
//             Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//             return true;
//         } catch (SignatureException e) {
//             logger.error(("Invalid JWT Signature: {}"), e.getMessage());
//         } catch (MalformedJwtException e) {
//             logger.error(("Invalid JWT Signature: {}"), e.getMessage());
//         } catch (ExpiredJwtException e) {
//             logger.error(("JWT token is expired: {}"), e.getMessage());
//         } catch (UnsupportedJwtException e) {
//             logger.error(("JWT token is unsupported: {}"), e.getMessage());
//         } catch (IllegalArgumentException e) {
//             logger.error(("JWT claims string is emptyyy: {}"), e.getMessage());
//         }
//         return false;
//     }
// }
