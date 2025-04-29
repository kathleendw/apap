package apapedia.order.dto.request;

import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateCartRequestDTO {
    // private Integer totalPrice;
    private List<CartItem> listCartItem;
}