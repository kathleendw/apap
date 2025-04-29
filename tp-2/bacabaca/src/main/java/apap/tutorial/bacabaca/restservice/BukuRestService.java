package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.rest.BukuDetail;
import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Mono;
import java.net.http.HttpResponse;

public interface BukuRestService {
    void createRestBuku(Buku buku);
    List<Buku> retrieveRestAllBuku();
    Buku getRestBukuById(UUID id);
    Mono<String> getStatus();
    Mono<BukuDetail> postStatus();
    Buku updateRestBuku(Buku buku);
    List<Buku> searchRestBuku(String judul);
}
