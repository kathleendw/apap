package apap.ti.silogistik2106637366.service;

import apap.ti.silogistik2106637366.model.Barang;
import java.util.List;

public interface BarangService {
    void saveBarang(Barang barang);
    List<Barang> getAllBarang();
    Barang getBarangBySku(String skuBarang);
    Barang ubahBarang(Barang barang);
    List<Barang> searchBarang(String skuBarang);
}
