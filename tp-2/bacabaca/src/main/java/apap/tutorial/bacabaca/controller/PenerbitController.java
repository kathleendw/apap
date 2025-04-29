package apap.tutorial.bacabaca.controller;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.DTO.PenerbitMapper;
import apap.tutorial.bacabaca.DTO.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Controller
public class PenerbitController {
    @Autowired
    PenerbitService penerbitService;
    @Autowired
    BukuService bukuService;
    @Autowired
    PenerbitMapper penerbitMapper;
    @GetMapping("penerbit/create")
    public String formAddPenerbit(Model model){
    //Membuat penerbitDto baru untuk diisi di form
        var penerbitDTO = new CreatePenerbitRequestDTO();
        //Menambah penerbitDTo ke model thymeleaf
        model.addAttribute( "penerbitDTO", penerbitDTO);
        return "form-create-penerbit";
    }
    @PostMapping("penerbit/create")
    public String addPenerbit(@ModelAttribute CreatePenerbitRequestDTO createPenerbitRequestDTO, Model model){
        //Membuat object Penerbit dengan data yang berasal dari DTO
        var penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(createPenerbitRequestDTO);
        //Memanggil Service createpenerbit
        penerbit = penerbitService.createPenerbit(penerbit);
        //Menambah penerbit ke model thymeleaf
        model. addAttribute( "penerbit", createPenerbitRequestDTO);
        return "success-create-penerbit" ;
    }
    @GetMapping("penerbit/viewall")
    public String listPenerbit(Model model){
        //Membuat penerbitDto baru untuk diisi di form
        var listPenerbit = penerbitService.getAllPenerbit();
        //Menambah penerbitDTo ke model thymeleaf
        model.addAttribute("listPenerbit", listPenerbit);
        return "viewall-penerbit" ;
    }
    @GetMapping("penerbit/{idPenerbit}")
    public String detailPenerbit(@PathVariable("idPenerbit") Long idPenerbit, Model model){
        var penerbit = penerbitService.getPenerbitById(idPenerbit);
        List<Buku> viewBukuPenerbit = new ArrayList<>();
        for (Buku buku : penerbit.getListBuku()) {
            if (buku.getIsDeleted() != null && buku.getIsDeleted()) {
                continue;
            } else {
                viewBukuPenerbit.add(buku);
            }
        }
        model.addAttribute("listBuku", viewBukuPenerbit);
        model.addAttribute("penerbit", penerbit);
        return "view-penerbit";
    }

    @GetMapping("penerbit/chart")
    public String chartPenerbit(Model model) {
        var listPenerbit = penerbitService.getPublisherBookCounts();
        model.addAttribute("listPenerbit", listPenerbit);

        return "view-penerbit-chart";
    }

}
