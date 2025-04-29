package apap.ti.silogistik2106637366.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import apap.ti.silogistik2106637366.model.Karyawan;
import apap.ti.silogistik2106637366.model.PermintaanPengirimanBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReadPermintaanPengirimanResponseDTO {
    private long idPermintaanPengiriman;
    private String nomorPengiriman;
    private String namaPenerima;
    private String alamatPenerima;
    private Date tanggalPengiriman;
    private Integer biayaPengiriman;
    private Integer jenisLayanan;
    private Date waktuPermintaan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
