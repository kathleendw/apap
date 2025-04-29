package apap.ti.silogistik2106637366.service;

import java.util.List;
import apap.ti.silogistik2106637366.repository.BarangDb;
import apap.ti.silogistik2106637366.model.Barang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Override
    public void saveBarang(Barang barang) {
        barangDb.save(barang);
    }

    @Override
    public List<Barang> getAllBarang() {
        return barangDb.findAll(); 
    }

    @Override
    public Barang getBarangBySku(String skuBarang) {
        for (Barang barang : getAllBarang()) {
            if (barang.getSkuBarang().equals(skuBarang)) {
                return barang;
            }
        }
        return null;
    }

    @Override
    public Barang ubahBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSkuBarang());
        if (barang != null){
            barang.setMerkBarang(barangFromDto.getMerkBarang());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    @Override
    public List<Barang> searchBarang(String skuBarang) {
        return barangDb.findBySkuBarang(skuBarang);
    }
}