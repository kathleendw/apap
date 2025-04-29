package apapedia.frontend_web.restservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apapedia.frontend_web.dto.auth.LoginRequest;
import apapedia.frontend_web.dto.auth.LoginResponse;
import apapedia.frontend_web.dto.auth.UserRequestDTO;
import apapedia.frontend_web.dto.auth.UserResponseDTO;

@Service
public class UserRestServiceImpl implements UserRestService{

    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
    }

    @Override
    public String getToken(String username, String name) {
        var body = new LoginRequest(username, name);

        var response = this.webClient
                        .post()
                        .uri("/api/authentication/login-jwt-seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(body)
                        .retrieve()
                        .bodyToMono(LoginResponse.class)
                        .block();

        var token = response.getJwtToken();

        return token;
    }

    @Override
    public UserResponseDTO sendUser(UserRequestDTO userDTO, String jwtToken) {
        try {
            var response = this.webClient
                .post()
                .uri("/api/user/add")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDTO)
                .retrieve()
                .bodyToMono(UserResponseDTO.class);
            var userSubmitted = response.block();
            System.out.println("Ini senduser hasil id " + userSubmitted.getId());
            return userSubmitted;
        } catch (Exception e) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            return userResponseDTO;
        }
    }
    
}
