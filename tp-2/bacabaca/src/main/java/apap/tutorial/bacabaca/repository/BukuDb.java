package apap.tutorial.bacabaca.repository;
import apap.tutorial.bacabaca.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
@Repository
public interface BukuDb extends JpaRepository<Buku, UUID> {
    List<Buku> findByJudulContainingIgnoreCase(String judul);
    List<Buku> findAllByOrderByJudulAsc();
    @Query("SELECT b FROM Buku b ORDER BY b.judulLower")
    List<Buku> sortBukuByJudulLower();
}