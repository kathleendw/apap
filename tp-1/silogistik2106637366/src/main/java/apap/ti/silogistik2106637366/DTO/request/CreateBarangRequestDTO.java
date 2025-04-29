package apap.ti.silogistik2106637366.DTO.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;
import apap.ti.silogistik2106637366.model.GudangBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateBarangRequestDTO {
    private Integer tipeBarang;
    private String merkBarang;
    private long hargaBarang;
    private List<GudangBarang> listGudangBarang;
}