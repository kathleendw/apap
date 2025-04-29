package apapedia.frontend_web.restservice;

import apapedia.frontend_web.dto.auth.UserRequestDTO;
import apapedia.frontend_web.dto.auth.UserResponseDTO;

public interface UserRestService {
    String getToken(String username, String name);   
    UserResponseDTO sendUser(UserRequestDTO userDTO, String jwtToken); 
} 
