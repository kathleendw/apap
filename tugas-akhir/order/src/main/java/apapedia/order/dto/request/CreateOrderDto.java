package apapedia.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    private UUID productId;
    private String productName;
    private int price;
    private UUID seller;
    private int quantity;
}
