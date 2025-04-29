package apap.ti.silogistik2106637366.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import apap.ti.silogistik2106637366.model.GudangBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UpdateGudangRequestDTO extends CreateGudangRequestDTO {
    private long idGudang;
    private List<GudangBarang> listGudangBarang;
}