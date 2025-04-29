package apap.ti.silogistik2106637366.repository;

import apap.ti.silogistik2106637366.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
    List<Barang> findBySkuBarang(String skuBarang);
}