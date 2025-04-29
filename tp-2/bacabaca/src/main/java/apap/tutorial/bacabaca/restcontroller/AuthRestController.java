package apap.tutorial.bacabaca.restcontroller;

import apap.tutorial.bacabaca.DTO.UserMapper;
import apap.tutorial.bacabaca.DTO.request.LoginJwtRequestDTO;
import apap.tutorial.bacabaca.DTO.request.LoginRequestDTO;
import apap.tutorial.bacabaca.DTO.response.LoginJwtResponseDTO;
import apap.tutorial.bacabaca.DTO.response.FailedResponseDTO;
import apap.tutorial.bacabaca.service.RoleService;
import apap.tutorial.bacabaca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/auth/login-jwt-webadmin")
    public ResponseEntity<?> loginJwtAdmin(@RequestBody LoginJwtRequestDTO loginJwtRequestDTO) {
        try {

            String jwtToken = userService.loginJwtAdmin(loginJwtRequestDTO);
            return new ResponseEntity<>(new LoginJwtResponseDTO(jwtToken), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            String jwtToken = userService.login(loginRequestDTO);
            if (jwtToken != null){
                return new ResponseEntity<>(new LoginJwtResponseDTO(jwtToken), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new FailedResponseDTO(false, "Username atau password salah"), HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
