package apap.ti.silogistik2106637366.DTO.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateKaryawanRequestDTO {
    private String namaKaryawan;
    private Integer jenisKelamin;
    private Date tanggalLahir;
}