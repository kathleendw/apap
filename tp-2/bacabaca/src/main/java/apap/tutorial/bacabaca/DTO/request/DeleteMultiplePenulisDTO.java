package apap.tutorial.bacabaca.DTO.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import apap.tutorial.bacabaca.model.Penulis;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteMultiplePenulisDTO { 
    private List<Penulis> listPenulis;
}
