package apap.ti.silogistik2106637366.service;

import apap.ti.silogistik2106637366.model.Karyawan;
import java.util.List;

public interface KaryawanService {
    Karyawan createKaryawan(Karyawan karyawan);
    List<Karyawan> getAllKaryawan();
}