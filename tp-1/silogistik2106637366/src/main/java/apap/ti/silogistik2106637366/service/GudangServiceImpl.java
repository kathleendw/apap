package apap.ti.silogistik2106637366.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106637366.repository.GudangDb;
import apap.ti.silogistik2106637366.model.Gudang;
import java.util.List;

@Service
public class GudangServiceImpl implements GudangService {

    @Autowired
    GudangDb gudangDb;

    @Override
    public Gudang createGudang(Gudang gudang) {
        return gudangDb.save(gudang); 
    }

    @Override
    public List<Gudang> getAllGudang() {
        return gudangDb.findAll(); 
    }

    @Override
    public Gudang getGudangById(long idGudang) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getIdGudang() == idGudang) {
                return gudang;
            }
        }
        return null;
    }

    @Override
    public Gudang updateGudang(Gudang gudangFromDto) {
        Gudang gudang = getGudangById(gudangFromDto.getIdGudang());
        if (gudang != null){
            gudang.setListGudangBarang(gudangFromDto.getListGudangBarang());
            gudangDb.save(gudang);
        }
        return gudang;
    }

}