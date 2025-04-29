package apap.tutorial.webadmin.restservice;

import apap.tutorial.webadmin.dto.UserRequestDTO;
import apap.tutorial.webadmin.dto.UserResponseDTO;

public interface UserRestService {
    String getToken(String username, String name);
    UserResponseDTO sendUser(UserRequestDTO userDTO, String jwtToken);
}
