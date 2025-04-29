package apapedia.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import apapedia.order.dto.request.CreateCartItemRequestDTO;
import apapedia.order.model.CartItem;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateCartItemRequestDTO extends CreateCartItemRequestDTO {
    private UUID itemId;
}