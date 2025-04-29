package apap.tutorial.bacabaca.DTO.request;
import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.model.Penulis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatePenerbitRequestDTO extends CreatePenerbitRequestDTO {
    private Long idPenerbit;
}