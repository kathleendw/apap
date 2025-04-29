package apap.ti.silogistik2106637366.DTO.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import apap.ti.silogistik2106637366.model.Karyawan;
import apap.ti.silogistik2106637366.model.PermintaanPengirimanBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreatePermintaanPengirimanRequestDTO {
    private String namaPenerima;
    private String alamatPenerima;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengiriman;
    private Integer biayaPengiriman;
    private Integer jenisLayanan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
