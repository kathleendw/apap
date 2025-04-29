package apap.ti.silogistik2106637366.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReadGudangResponseDTO {
    private long idGudang;
    private String namaGudang;
    private String alamatGudang;
}
