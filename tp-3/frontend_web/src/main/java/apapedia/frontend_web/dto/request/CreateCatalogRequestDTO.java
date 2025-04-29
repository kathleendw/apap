package apapedia.frontend_web.dto.request;

import java.math.BigDecimal;
import java.util.Locale.Category;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import jakarta.validation.constraints.Min;

@Data
public class CreateCatalogRequestDTO {
    @NotNull
    @Positive
    @Positive(message = "Harga tidak boleh negatif")
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

    private UUID sellerId;
}
