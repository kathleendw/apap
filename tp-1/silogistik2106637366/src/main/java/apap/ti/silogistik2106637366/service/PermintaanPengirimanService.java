package apap.ti.silogistik2106637366.service;

import apap.ti.silogistik2106637366.model.PermintaanPengiriman;
import java.util.List;

public interface PermintaanPengirimanService {
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    PermintaanPengiriman getPermintaanPengirimanById(long idPermintaanPengiriman);
    void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
}
