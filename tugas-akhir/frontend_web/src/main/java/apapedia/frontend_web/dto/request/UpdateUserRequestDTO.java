package apapedia.frontend_web.dto.request;

import apapedia.frontend_web.dto.request.CreateUserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDTO{
    private String nameUser;
    private String username;
    private String password;
    private String email;
    private String address;
    private UUID idUser;
}
