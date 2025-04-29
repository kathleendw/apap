package apapedia.catalog.dto.request;

import apapedia.catalog.model.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCatalogRequestDTO {
    // @NotNull
    private UUID sellerId;
    @NotNull
    @Positive
    private Integer price;
    @NotNull
    private String productName;
    @NotNull
    private String productDescription;
    @NotNull
    private UUID category;
    @PositiveOrZero
    @NotNull
    private Integer stock;
    @NotNull
    private String imagePath;
}
