package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.repository.BukuDb;
import apap.tutorial.bacabaca.rest.BukuDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.BodyInserters;
import java.net.http.HttpResponse;


@Service
@Transactional

public class BukuRestServiceImpl implements BukuRestService {
    @Autowired
    private BukuDb bukuDb;

    @Override
    public void createRestBuku(Buku buku) { 
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> retrieveRestAllBuku() { 
        return bukuDb.sortBukuByJudulLower();
    };

    @Override
    public Buku getRestBukuById(UUID id) {
        for (Buku buku : retrieveRestAllBuku()) {
            if (buku.getId().equals(id)) {
                return buku;
            }
        }
        return null;
    };

    private final WebClient webClient;
    private final String mockUrl = "https://9320ff50-7334-4ff3-b33e-ae5e82f8b20d.mock.pstmn.io";

    private final WebClient webClientTranslate;
    private final String translationApiUrl = "https://text-translator2.p.rapidapi.com";

    public BukuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(mockUrl).build();
        this.webClientTranslate = webClientBuilder.baseUrl(translationApiUrl).build();
    }

    @Override
    public Mono<String> getStatus(){
        return this.webClient.get().uri("/rest/buku/status").retrieve().bodyToMono(String.class);
    };

    @Override
    public Mono<BukuDetail> postStatus(){
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("judul", "Buku Cara Membaca Jilid 2");
        data.add("tahunTerbit", "2003");
        var response = this.webClient
            .post()
            .uri("/rest/buku/full-status")
            .bodyValue(data)
            .retrieve()
            .bodyToMono(BukuDetail.class);
        return response;
    }

    @Override
    public Buku updateRestBuku(Buku bukuDTO) {
        Buku buku = getRestBukuById(bukuDTO.getId());
        if (buku != null) {
            buku.setHarga(bukuDTO.getHarga());
            buku.setJudul (bukuDTO.getJudul());
            buku.setListPenulis(bukuDTO.getListPenulis());
            buku.setTahunTerbit(bukuDTO.getTahunTerbit());
            buku.setPenerbit(bukuDTO.getPenerbit());
            bukuDb.save(buku);
        }
        return buku;
    }

    @Override
    public List<Buku> searchRestBuku(String judul) {
        return bukuDb.findByJudulContainingIgnoreCase(judul);
    }

}
