package apapedia.order.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import apapedia.order.model.CartItem;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponseDTO {
    private UUID cartId;
    private Integer totalPrice;
    private UUID userId;
    private List<CartItem> listCartItem;
}
