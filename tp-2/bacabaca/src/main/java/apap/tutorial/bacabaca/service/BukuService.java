package apap.tutorial.bacabaca.service;
import apap.tutorial.bacabaca.model.Buku;
import java.util.List;
import java.util.UUID;
public interface BukuService {
    void saveBuku(Buku buku);
    List<Buku> getAllBuku();
    Buku getBukuById(UUID id);
    boolean isJudulExist(String judul);
    boolean isJudulExist(String judul, UUID id);
    Buku updateBuku(Buku buku);
    void deleteBuku(Buku buku);
    List<Buku> searchBuku(String judul);
    List<Buku> getAllSortedBuku();
    
 }
