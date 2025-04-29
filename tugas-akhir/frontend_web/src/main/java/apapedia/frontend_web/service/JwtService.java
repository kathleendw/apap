package apapedia.frontend_web.service;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {

    String parseJwt(HttpServletRequest request);

    String getUserNameFromJwtToken(String token);

    String getIdFromJwtToken(String token);

    String getRoleFromJwtToken(String token);

    boolean validateJwtToken(String authToken);

}
