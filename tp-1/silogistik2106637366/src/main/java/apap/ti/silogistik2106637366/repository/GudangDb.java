package apap.ti.silogistik2106637366.repository;

import apap.ti.silogistik2106637366.model.Gudang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GudangDb extends JpaRepository<Gudang, String> {
}