package apapedia.frontend_web.service;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class JwtServiceImpl implements JwtService{

    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    @Override
    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }

    @Override
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey("OI4OVNONVONI2H3R238R92381983R919").parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public String getIdFromJwtToken(String token) {
        return Jwts.parser().setSigningKey("OI4OVNONVONI2H3R238R92381983R919").parseClaimsJws(token).getBody().get("id", String.class);
    }

    @Override
    public String getRoleFromJwtToken(String token) {
        return Jwts.parser().setSigningKey("OI4OVNONVONI2H3R238R92381983R919").parseClaimsJws(token).getBody().get("role", String.class);
    }

    @Override
    public boolean validateJwtToken(String authToken) {
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
