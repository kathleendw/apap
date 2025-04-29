package apap.ti.silogistik2106637366.service;

import java.util.List;
import apap.ti.silogistik2106637366.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106637366.model.PermintaanPengiriman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAll(); 
    }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(long idPermintaanPengiriman) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getIdPermintaanPengiriman() == idPermintaanPengiriman) {
                return permintaanPengiriman;
            }
        }
        return null;
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman){
        permintaanPengiriman.setIsCancelled(true);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }

}