package apap.tutorial.bacabaca.DTO.request;
import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.model.Penulis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBukuRequestDTO {
    @NotBlank(message = "Please fill the required field")
    private String judul;

    @Pattern(regexp = "\\d{4}", message = "Please enter a valid year")
    private String tahunTerbit;

    @Positive(message = "Please enter a valid price")
    private BigDecimal harga;
    
    @NotNull(message = "Please fill the required field")
    private Penerbit penerbit;

    private List<Penulis> listPenulis;
}