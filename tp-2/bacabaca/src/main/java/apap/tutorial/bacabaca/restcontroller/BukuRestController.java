package apap.tutorial.bacabaca.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import apap.tutorial.bacabaca.DTO.BukuMapper;
import apap.tutorial.bacabaca.rest.BukuDetail;
import apap.tutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.request.TranslateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.response.TranslateBukuResponseDTO;
import apap.tutorial.bacabaca.restservice.BukuRestService;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import apap.tutorial.bacabaca.model.Buku;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.ui.Model;
import java.util.Random;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api")

public class BukuRestController {
    @Autowired
    private BukuMapper bukuMapper;

    @Autowired
    private BukuRestService bukuRestService;

    @GetMapping(value = "/buku/view-all")
    private List<Buku> retrieveAllBuku(){
        return bukuRestService.retrieveRestAllBuku();
    }

    @GetMapping(value= "/buku/{id}")
    private Buku retrieveBuku(@PathVariable("id") String id){
        try {
            return bukuRestService.getRestBukuById(UUID.fromString(id));
        }
        catch(NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Buku " + id + " not found");
        }
    }

    @PostMapping(value = "/buku/create")
    public Buku restAddBuku(@Valid @RequestBody CreateBukuRequestDTO bukuDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else  {
            var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
            bukuRestService.createRestBuku(buku);
            return buku;
        }
    }

    @RequestMapping(value= "/buku", method = RequestMethod.PUT)
    public Buku restUpdateBuku(@Valid @RequestBody UpdateBukuRequestDTO bukuDTO, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else  {
            var buku = bukuMapper.updateBukuRequestDTOToBuku(bukuDTO);
            bukuRestService.updateRestBuku(buku);
            return buku;
        }
    }

    @GetMapping(value= "/buku/search")
    public List<Buku> restSearchBuku(Model model, @RequestParam(value = "query", required = false) String query) {
        List<Buku> listBuku = new ArrayList<>();
        if (query != null && !query.isEmpty()) {
            listBuku = bukuRestService.searchRestBuku(query);
        }
        List<Buku> viewBuku = new ArrayList<>();
        for (Buku buku : listBuku) {
            if (buku.getIsDeleted() != null && buku.getIsDeleted()) {
                continue;
            } else {
                viewBuku.add(buku);
            }
        }
        return viewBuku;
    }

    @GetMapping("/random")
    public ResponseEntity random(){
        Random random = new Random();
        var theBool = random.nextBoolean();
        if (theBool){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/buku/status")
    private Mono<String> getStatus(){
        return bukuRestService.getStatus();
    }

    @PostMapping(value = "/full")
    private Mono<BukuDetail> postStatus(){
        return bukuRestService.postStatus();
    }

    @PostMapping(value = "/buku/translate")
    private Buku postTranslate(@RequestBody TranslateBukuRequestDTO translateBukuRequestDTO, UpdateBukuRequestDTO updateBukuRequestDTO) {
        String judulBuku = bukuRestService.getRestBukuById(translateBukuRequestDTO.getBookId()).getJudul();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
            .header("content-type", "application/x-www-form-urlencoded")
            .header("X-RapidAPI-Key", "bd17bd7fd3msh096136c44690ae8p132094jsndb1adfe546d8")
            .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
            .method("POST", HttpRequest.BodyPublishers.ofString("source_language=en&target_language=id&text=" + URLEncoder.encode(judulBuku, StandardCharsets.UTF_8)))
            .build();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            TranslateBukuResponseDTO translateBukuResponseDTO = objectMapper.readValue(responseBody, TranslateBukuResponseDTO.class);
            String translatedText = translateBukuResponseDTO.getData().getTranslatedText();
            Buku buku = bukuRestService.getRestBukuById(translateBukuRequestDTO.getBookId());
            buku.setJudulLower(translatedText);
            return buku;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
