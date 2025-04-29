package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Sertifikasi;
import apap.tutorial.bacabaca.DTO.PenulisMapper;
import apap.tutorial.bacabaca.DTO.request.CreatePenulisRequestDTO;
import apap.tutorial.bacabaca.DTO.request.DeleteMultiplePenulisDTO;
import apap.tutorial.bacabaca.service.PenulisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@Controller
public class PenulisController {

    @Autowired
    PenulisService penulisService;

    @Autowired
    PenulisMapper penulisMapper;

    @GetMapping("penulis/create")
    public String formAddPenulis(Model model) {
        // Membuat DTO untuk dikirimkan ke view.
        var penulisDTO = new CreatePenulisRequestDTO();
        model.addAttribute("penulisDTO", penulisDTO);
        return "form-create-penulis" ;
    }

    @PostMapping("penulis/create")
    public String addPenulis(
        @ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO,
        Model model
    ) {
        //Dengan bantuan mapper, buat object Penulis dengan data yang berasal dari DTO.
        var penulis = penulisMapper.createPenulisRequestDTOToPenulis(createPenulisRequestDTO);
        for (Sertifikasi sertifikasi : createPenulisRequestDTO.getListSertifikasi()) {
            sertifikasi.setPenulis(penulis);
        }
        penulisService.createPenulis(penulis);
        model.addAttribute("penulis", createPenulisRequestDTO);
        return "success-create-penulis" ;
    }

    @PostMapping(value = "penulis/create", params = {"addRow"})
    public String addRowSertifikasiPenulis (@ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO, Model model) {
        if (createPenulisRequestDTO.getListSertifikasi() == null || createPenulisRequestDTO.getListSertifikasi().size() == 0) { 
            createPenulisRequestDTO.setListSertifikasi(new ArrayList<>());
        }

        createPenulisRequestDTO.getListSertifikasi().add(new Sertifikasi());

        model.addAttribute("penulisDTO", createPenulisRequestDTO);
        return "form-create-penulis";
    }

    @PostMapping(value = "penulis/create", params = {"deleteRow"}) 
    public String deleteRowSertifikasiPenulis (@ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO, @RequestParam("deleteRow") int row, Model model) {
        createPenulisRequestDTO.getListSertifikasi().remove(row);
        model.addAttribute("penulisDTO", createPenulisRequestDTO);
        return "form-create-penulis";
    }

    @GetMapping("penulis/viewall")
    public String listPenulis(Model model) {
        var listPenulis = penulisService.getAllPenulis();
        var deleteDTO = new DeleteMultiplePenulisDTO();
        model.addAttribute("listPenulis", listPenulis);
        model.addAttribute("deleteDTO", deleteDTO);
        return "viewall-penulis";
    }

    @PostMapping("penulis/delete")
    public String deleteMultiplePenulis(@ModelAttribute DeleteMultiplePenulisDTO deleteDTO) {
        penulisService.deleteListPenulis(deleteDTO.getListPenulis()); 
        return "success-delete-penulis";
    }

}


