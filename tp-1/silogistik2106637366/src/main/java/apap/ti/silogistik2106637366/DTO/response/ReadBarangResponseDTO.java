package apap.ti.silogistik2106637366.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReadBarangResponseDTO {
    private String skuBarang;
    private Integer tipeBarang;
    private String merkBarang;
    private long hargaBarang;
}
