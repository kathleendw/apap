package apapedia.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogDTO {
    private UUID id;
    private UUID sellerId;
    private int price;
    private String productName;
    private String productDescription;
    private int stock;
    private String imagePath;
}