package apap.tutorial.bacabaca.controller;
import apap.tutorial.bacabaca.DTO.BukuMapper;
import apap.tutorial.bacabaca.controller.DTO.BukuDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penulis;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import apap.tutorial.bacabaca.service.PenulisService;
import apap.tutorial.bacabaca.DTO.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.DTO.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.repository.BukuDb;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import apap.tutorial.bacabaca.DTO.response.ReadBukuResponseDTO;


import java.util.List;
import java.util.UUID;

@Controller
public class BukuController {
 
    @Autowired
    private BukuMapper bukuMapper;
    @Autowired
    private BukuService bukuService;
    @Autowired
    private PenerbitService penerbitService;
    @Autowired
    private PenulisService penulisService;


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/viewall-with-datatables")
    public String listBuku(Model model, @RequestParam(name = "search", required = false) String search) {
        List<Buku> listBuku;
        if (search != null && !search.isEmpty()) {
            listBuku = bukuService.searchBuku(search);
        } else {
            listBuku = bukuService.getAllSortedBuku();
        }
        List<Buku> viewBuku = new ArrayList<>();
        for (Buku buku : listBuku) {
            if (buku.getIsDeleted() != null && buku.getIsDeleted()) {
                continue;
            } else {
                viewBuku.add(buku);
            }
        }
        model.addAttribute("listBuku", viewBuku);
        return "viewall-buku-with-datatables";
    }
    
    /* @GetMapping("buku/{id}")
    public String detailBuku(@PathVariable("id") UUID id, Model model){
        var buku = bukuService.getBukuById(id);
        model.addAttribute("buku", buku);
        return "view-buku";
    } */

    @GetMapping("buku/{id}")
    public String detailBuku(@PathVariable("id") UUID id, Model model) {
        Buku buku = bukuService.getBukuById(id);
        ReadBukuResponseDTO readBukuResponseDTO = bukuMapper.bukuToReadBukuResponseDTO(buku);
        model.addAttribute("buku", readBukuResponseDTO);
        return "view-buku";
    }

    @GetMapping("buku/create")
    public String formAddBuku (Model model) {
        // Kirim bukuDTO untuk nantinya isian field di-bind ke sini.
        var bukuDTO = new CreateBukuRequestDTO();
        model.addAttribute("bukuDTO", bukuDTO);

        // Kirim list penerbit untuk menjadi pilihan pada dropdown.
        var listPenerbit = penerbitService.getAllPenerbit(); 
        model.addAttribute("listPenerbit", listPenerbit);

        // Kirim list penulis untuk menjadi pilihan pada dropdown.
        var listPenulisExisting = penulisService.getAllPenulis();
        model.addAttribute("listPenulisExisting", listPenulisExisting);
        return "form-create-buku";
    }

    @PostMapping("buku/create")
    public String addBuku(@Valid @ModelAttribute CreateBukuRequestDTO bukuDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String judulErrorMessage = "";
            FieldError judulInvalid = bindingResult.getFieldError("judul");
            if (judulInvalid != null) {
                judulErrorMessage = judulInvalid.getDefaultMessage();
            }
            String tahunErrorMessage = "";
            FieldError tahunInvalid = bindingResult.getFieldError("tahunTerbit");
            if (tahunInvalid != null) {
                tahunErrorMessage = tahunInvalid.getDefaultMessage();
            }
            String hargaErrorMessage = "";
            FieldError hargaInvalid = bindingResult.getFieldError("harga");
            if (hargaInvalid != null) {
                hargaErrorMessage = hargaInvalid.getDefaultMessage();
            }
            String  penerbitErrorMessage = "";
            FieldError penerbitInvalid = bindingResult.getFieldError("penerbit");
            if (penerbitInvalid != null) {
                 penerbitErrorMessage = penerbitInvalid.getDefaultMessage();
            }
            model.addAttribute("judulErrorMessage", judulErrorMessage);
            model.addAttribute("tahunErrorMessage", tahunErrorMessage);
            model.addAttribute("hargaErrorMessage", hargaErrorMessage);
            model.addAttribute("penerbitErrorMessage", penerbitErrorMessage);
            return "error-view";
        }
        if (bukuService.isJudulExist(bukuDTO.getJudul())){
            var errorMessage = "Maaf, judul buku sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view" ;
        }
        var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
        //Memanggil Service addBuku
        bukuService.saveBuku(buku);
        //Add variabel id buku ke 'id' untuk dirender di thymeleaf
        model.addAttribute("id", buku.getId());
        //Add variabel judul ke 'judul' untuk dirender di thymeleaf
        model.addAttribute( "judul",buku.getJudul());
        return "success-create-buku" ;
    }

    @GetMapping("buku/{id}/update")
    public String formUpdateBuku(@PathVariable("id") UUID id, Model model) {
        //Mengambil buku dengan id tersebut
        var buku = bukuService.getBukuById(id);
        //Memindahkan data buku ke DTO untuk selanjutnya diubah di form pengguna
        var bukuDTO = bukuMapper.bukuToUpdateBukuRequestDTO(buku);
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", bukuDTO);
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        return "form-update-buku";
    }

    @PostMapping("buku/update")
    public String updateBuku(@Valid @ModelAttribute UpdateBukuRequestDTO bukuDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            String judulErrorMessage = "";
            FieldError judulInvalid = bindingResult.getFieldError("judul");
            if (judulInvalid != null) {
                judulErrorMessage = judulInvalid.getDefaultMessage();
            }
            String tahunErrorMessage = "";
            FieldError tahunInvalid = bindingResult.getFieldError("tahunTerbit");
            if (tahunInvalid != null) {
                tahunErrorMessage = tahunInvalid.getDefaultMessage();
            }
            String hargaErrorMessage = "";
            FieldError hargaInvalid = bindingResult.getFieldError("harga");
            if (hargaInvalid != null) {
                hargaErrorMessage = hargaInvalid.getDefaultMessage();
            }
            String  penerbitErrorMessage = "";
            FieldError penerbitInvalid = bindingResult.getFieldError("penerbit");
            if (penerbitInvalid != null) {
                 penerbitErrorMessage = penerbitInvalid.getDefaultMessage();
            }
            model.addAttribute("judulErrorMessage", judulErrorMessage);
            model.addAttribute("tahunErrorMessage", tahunErrorMessage);
            model.addAttribute("hargaErrorMessage", hargaErrorMessage);
            model.addAttribute("penerbitErrorMessage", penerbitErrorMessage);
            return "error-view";
        }
        if(bukuService.isJudulExist(bukuDTO.getJudul(), bukuDTO.getId())){
            var errorMessage = "Maaf, judul buku sudah ada";
            model. addAttribute("errorMessage", errorMessage);
            return "error-view" ;
        }
        var bukuFromDTO = bukuMapper.updateBukuRequestDTOToBuku(bukuDTO);
        //Memanggil Service addBuku
        var buku = bukuService.updateBuku(bukuFromDTO);
        //Add variabel kode buky ke 'kode' untuk dirender di thymeleaf
        model.addAttribute("id", buku.getId());
        //Add variabel judul ke 'judul' untuk dirender di thymeleaf
        model.addAttribute("judul", buku.getJudul());
        return "success-update-buku" ;
    }
    @GetMapping("buku/{id}/delete")
    public String deleteBuku(@PathVariable("id") UUID id, Model model) {
        var buku = bukuService.getBukuById(id);
        bukuService.deleteBuku(buku);
        model.addAttribute("id", id);
        return "success-delete-buku" ;
    }

    @PostMapping(value = "buku/create", params = {"addRow"})
    public String addRowPenulisBuku (@ModelAttribute CreateBukuRequestDTO createBukuRequestDTO, Model model) {
        if (createBukuRequestDTO.getListPenulis() == null || createBukuRequestDTO.getListPenulis().size() == 0) { 
            createBukuRequestDTO.setListPenulis(new ArrayList<>());
        }

        // Memasukkan Penulis baru (kosong) ke list, untuk dirender sebagai row baru. 
        createBukuRequestDTO.getListPenulis().add(new Penulis());

        // Kirim list penerbit penulis untuk menjadi pilihan pada dropdown.
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", createBukuRequestDTO);
        return "form-create-buku";
    }

    @PostMapping(value = "buku/create", params = {"deleteRow"}) 
    public String deleteRowPenulisBuku (@ModelAttribute CreateBukuRequestDTO createBukuRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        createBukuRequestDTO.getListPenulis().remove(row);
        model.addAttribute("bukuDTO", createBukuRequestDTO);
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis()); 
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        return "form-create-buku";
    }

    @PostMapping(value = "buku/update", params = {"addRow"})
    public String addRowPenulisBukuUpdate (@ModelAttribute UpdateBukuRequestDTO updateBukuRequestDTO, Model model) {
        if (updateBukuRequestDTO.getListPenulis() == null || updateBukuRequestDTO.getListPenulis().size() == 0) { 
            updateBukuRequestDTO.setListPenulis(new ArrayList<>());
        }

        // Memasukkan Penulis baru (kosong) ke list, untuk dirender sebagai row baru. 
        updateBukuRequestDTO.getListPenulis().add(new Penulis());

        // Kirim list penerbit penulis untuk menjadi pilihan pada dropdown.
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis()); 
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", updateBukuRequestDTO);
        return "form-update-buku";
    }

    @PostMapping(value = "buku/update", params = {"deleteRow"}) 
    public String deleteRowPenulisBukuUpdate (@ModelAttribute UpdateBukuRequestDTO updateBukuRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        updateBukuRequestDTO.getListPenulis().remove(row);
        model.addAttribute("bukuDTO", updateBukuRequestDTO);
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis()); 
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        return "form-update-buku";
    }

}
