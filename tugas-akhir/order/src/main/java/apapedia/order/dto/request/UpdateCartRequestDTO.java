package apapedia.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import apapedia.order.dto.request.CreateCartRequestDTO;
import apapedia.order.model.Cart;
import apapedia.order.dto.request.CreateCartRequestDTO;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateCartRequestDTO extends CreateCartRequestDTO {
    private UUID cartId;
}