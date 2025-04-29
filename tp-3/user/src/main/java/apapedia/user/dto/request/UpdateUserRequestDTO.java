package apapedia.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import apapedia.user.dto.request.CreateUserRequestDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequestDTO extends CreateUserRequestDTO {
    private UUID idUser;
}
