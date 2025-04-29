package apap.ti.silogistik2106637366.service;

import apap.ti.silogistik2106637366.model.Gudang;
import java.util.List;

public interface GudangService {
    Gudang createGudang(Gudang gudang);
    List<Gudang> getAllGudang();
    Gudang getGudangById(long idGudang);
    Gudang updateGudang(Gudang gudang);
}