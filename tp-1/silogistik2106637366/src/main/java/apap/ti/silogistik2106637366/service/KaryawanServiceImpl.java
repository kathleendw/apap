package apap.ti.silogistik2106637366.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import apap.ti.silogistik2106637366.repository.KaryawanDb;
import apap.ti.silogistik2106637366.model.Karyawan;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public Karyawan createKaryawan(Karyawan karyawan) {
        return karyawanDb.save(karyawan); 
    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDb.findAll(); 
    }

}