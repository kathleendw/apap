package apapedia.frontend_web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import apapedia.frontend_web.dto.response.OrderDTO;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
// @JsonIgnoreProperties(value={"orderId"}, allowSetters = true)
public class OrderItemDTO {
    private UUID itemId;
    private UUID productId;
    private OrderDTO order;
    private Integer quantity;
    private String productName;
    private Integer productPrice;
    private Boolean isDeleted;
}
