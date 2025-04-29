package apapedia.frontend_web.dto.request;

import apapedia.frontend_web.dto.response.CategoryDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCatalogRequestDTO {
    @NotNull
    private UUID id;
    @NotNull
    private UUID sellerId;
    @NotNull
    @Positive
    private Integer price;
    @NotNull
    private String productName;
    @NotNull
    private String productDescription;
    @NotNull
    private CategoryDTO category;
    @PositiveOrZero
    @NotNull
    private Integer stock;
    @NotNull
    private String imagePath;
}
