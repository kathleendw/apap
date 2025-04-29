package apapedia.frontend_web.dto.response;

import java.util.UUID;

import apapedia.frontend_web.dto.request.CreateUserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO extends CreateUserRequestDTO{
    private UUID cartId;
}
