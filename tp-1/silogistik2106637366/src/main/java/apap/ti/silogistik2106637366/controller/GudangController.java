package apap.ti.silogistik2106637366.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import java.util.List;
import java.util.ArrayList;

import apap.ti.silogistik2106637366.DTO.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106637366.DTO.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106637366.DTO.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106637366.DTO.GudangMapper;
import apap.ti.silogistik2106637366.service.GudangService;
import apap.ti.silogistik2106637366.service.BarangService;
import apap.ti.silogistik2106637366.model.Gudang;
import apap.ti.silogistik2106637366.model.Barang;
import apap.ti.silogistik2106637366.model.GudangBarang;

@Controller
public class GudangController {

    @Autowired
    private GudangMapper gudangMapper;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private BarangService barangService;

    @GetMapping("gudang")
    public String listGudang(Model model) {
        List<Gudang> listGudang = gudangService.getAllGudang();
        model.addAttribute("listGudang", listGudang);
        return "viewall-gudang";
    }

    @GetMapping("gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") long idGudang, Model model) {
        Gudang gudang = gudangService.getGudangById(idGudang);
        ReadGudangResponseDTO readGudangResponseDTO = gudangMapper.gudangToReadGudangResponseDTO(gudang);
        model.addAttribute("gudang", readGudangResponseDTO);

        List<Barang> listBarang = new ArrayList<>();
        List<Integer> listStok = new ArrayList<>();
        for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
            listBarang.add(gudangBarang.getBarang());
            listStok.add(gudangBarang.getStok());
        }
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listStok", listStok);

        return "view-gudang";
    }

    @GetMapping("gudang/{idGudang}/restock-barang")
    public String formRestockBarang(@PathVariable("idGudang") long idGudang, Model model) {
        var gudangDTO = new CreateGudangRequestDTO();
        model.addAttribute("gudangDTO", gudangDTO);

        var gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);
        
        var listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);
        return "form-restock-barang";
    }

    @PostMapping("gudang/{idGudang}/restock-barang")
    public String restockBarang(@PathVariable("idGudang") long idGudang, @ModelAttribute UpdateGudangRequestDTO gudangDTO, Model model) {
        var gudang = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);
        for (GudangBarang gudangBarang : gudangDTO.getListGudangBarang()) {
            gudangBarang.setGudang(gudang);
        }
        gudangService.updateGudang(gudang);
        model.addAttribute("idGudang", gudang.getIdGudang());
        return "success-restock-barang";
    }

    @PostMapping(value = "gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowRestockBarang(@PathVariable("idGudang") long idGudang, @ModelAttribute CreateGudangRequestDTO createGudangRequestDTO, Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        if (createGudangRequestDTO.getListGudangBarang() == null || createGudangRequestDTO.getListGudangBarang().size() == 0) { 
            createGudangRequestDTO.setListGudangBarang(new ArrayList<>());
        }
        createGudangRequestDTO.getListGudangBarang().add(new GudangBarang());
        model.addAttribute("gudangDTO", createGudangRequestDTO);
        model.addAttribute("gudang", gudang);
        model.addAttribute("idGudang", gudang.getIdGudang());

        var listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);

        return "form-restock-barang";
    } 

    @GetMapping("gudang/cari-barang")
    public String cariBarang(Model model, @RequestParam(name = "search", required = false) String search) {
        List<Barang> listBarang = new ArrayList<>();
        if (search != null && !search.isEmpty()) {
            listBarang = barangService.searchBarang(search);
        } 
        List<Gudang> listGudang = new ArrayList<>();
        List<Integer> listStok = new ArrayList<>();
        for (Barang barang : listBarang) {
            for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
                listGudang.add(gudangBarang.getGudang());
                listStok.add(gudangBarang.getStok());
            }
        }
        List<Gudang> viewGudang = new ArrayList<>();
        for (Gudang gudang : listGudang) {
            viewGudang.add(gudang);
        }
        model.addAttribute("listGudang", viewGudang);
        model.addAttribute("listStok", listStok);

        var listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);

        return "cari-barang";
    }

}