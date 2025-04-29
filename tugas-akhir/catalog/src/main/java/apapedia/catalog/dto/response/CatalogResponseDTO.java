package apapedia.catalog.dto.response;

import java.util.UUID;

import apapedia.catalog.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogResponseDTO {

    private UUID seller;
    
    private Integer price;
    
    private String productName;
    
    private String productDescription;
    
    private UUID category;
    
    private Integer stok;
    
    private String image;
}
