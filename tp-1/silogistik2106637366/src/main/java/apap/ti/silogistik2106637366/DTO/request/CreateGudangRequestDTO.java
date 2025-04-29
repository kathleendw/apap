package apap.ti.silogistik2106637366.DTO.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import apap.ti.silogistik2106637366.model.GudangBarang;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateGudangRequestDTO {
    private String namaGudang;
    private String alamatGudang;
    private List<GudangBarang> listGudangBarang;
}