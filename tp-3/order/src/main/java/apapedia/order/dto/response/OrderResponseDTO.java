package apapedia.order.dto.response;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import apapedia.order.model.OrderItem;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDTO {
    private UUID id;
    private Date createdAt;
    private Date updatedAt;
    private Integer status;
    private Integer totalPrice;
    private UUID customer;
    private UUID seller;
    private List<OrderItem> orderItem;
}
