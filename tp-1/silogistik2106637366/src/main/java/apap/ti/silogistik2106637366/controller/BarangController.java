package apap.ti.silogistik2106637366.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import apap.ti.silogistik2106637366.DTO.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106637366.DTO.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106637366.DTO.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106637366.DTO.BarangMapper;
import apap.ti.silogistik2106637366.service.BarangService;
import apap.ti.silogistik2106637366.service.GudangService;
import apap.ti.silogistik2106637366.service.KaryawanService;
import apap.ti.silogistik2106637366.service.PermintaanPengirimanService;
import apap.ti.silogistik2106637366.model.Barang;
import apap.ti.silogistik2106637366.model.Gudang;
import apap.ti.silogistik2106637366.model.Karyawan;
import apap.ti.silogistik2106637366.model.PermintaanPengiriman;
import apap.ti.silogistik2106637366.model.GudangBarang;

@Controller
public class BarangController {

    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private BarangService barangService;

    @Autowired
    private GudangService gudangService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;
 
    @GetMapping("/")
    public String beranda(Model model){
        List<Barang> listBarang = barangService.getAllBarang();
        model.addAttribute("totalBarang", listBarang.size());

        List<Gudang> listGudang = gudangService.getAllGudang();
        model.addAttribute("totalGudang", listGudang.size());

        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        model.addAttribute("totalKaryawan", listKaryawan.size());

        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();
        model.addAttribute("totalPermintaanPengiriman", listPermintaanPengiriman.size());

        Map<Gudang,Integer> listGudangStok = new HashMap<>();
        for (Gudang gudang : listGudang) {
            int totalStok = 0;
            for (GudangBarang gudangBarang : gudang.getListGudangBarang()) {
                totalStok += gudangBarang.getStok();
            }
            listGudangStok.put(gudang, totalStok);
        }
        Map.Entry<Gudang, Integer> max = null;
        for (Map.Entry<Gudang, Integer> entry : listGudangStok.entrySet()) {
            if (max == null || entry.getValue() > max.getValue()) {
                max = entry;
            }
        }
        model.addAttribute("gudang", max.getKey().getNamaGudang());
        model.addAttribute("stok", max.getValue());
        return "beranda";
    }

    @GetMapping("barang")
    public String listBarang(Model model) {
        List<Barang> listBarang = barangService.getAllBarang();

        List<Integer> listTotalStok = new ArrayList<>();
        for (Barang barang : listBarang) {
            int totalStok = 0;
            for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
                totalStok += gudangBarang.getStok();
            }
            listTotalStok.add(totalStok);
        }
        model.addAttribute("listTotalStok", listTotalStok);
        model.addAttribute("listBarang", listBarang);
        return "viewall-barang";
    }

    @GetMapping("barang/tambah")
    public String formTambahBarang(Model model) {
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);
        return "form-tambah-barang";
    }

    private static int autoIncrement = 1; 
    @PostMapping("barang/tambah")
    public String tambahBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, Model model) {
        String tipeBarang = getTipeBarangByValue(barangDTO.getTipeBarang());
        String skuBarang = tipeBarang + String.format("%03d", autoIncrement);
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        barang.setSkuBarang(skuBarang);
        barangService.saveBarang(barang);
        autoIncrement++;
        model.addAttribute("skuBarang", barang.getSkuBarang());
        return "success-tambah-barang";
    }

    private String getTipeBarangByValue(Integer value) {
        switch (value) {
            case 1:
                return "ELEC";
            case 2:
                return "CLOT";
            case 3:
                return "FOOD";
            case 4:
                return "COSM";
            case 5:
                return "TOOL";
        }
        return "";
    }

    @GetMapping("barang/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model) {
        Barang barang = barangService.getBarangBySku(sku);
        ReadBarangResponseDTO readBarangResponseDTO = barangMapper.barangToReadBarangResponseDTO(barang);

        List<Gudang> listGudang = new ArrayList<>();
        List<Integer> listStok = new ArrayList<>();
        int totalStok = 0;
        for (GudangBarang gudangBarang : barang.getListGudangBarang()) {
            listGudang.add(gudangBarang.getGudang());
            listStok.add(gudangBarang.getStok());
            totalStok += gudangBarang.getStok();
        }
        model.addAttribute("barang", readBarangResponseDTO);
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("totalStok", totalStok);
        model.addAttribute("listStok", listStok);
        return "view-barang";
    }

    @GetMapping("barang/ubah/{sku}")
    public String formUbahBarang(@PathVariable("sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);
        model.addAttribute("barangDTO", barangDTO);
        return "form-ubah-barang";
    }

    @PostMapping("barang/ubah")
    public String ubahBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, Model model) {
        var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.ubahBarang(barangFromDTO);
        model.addAttribute("skuBarang", barang.getSkuBarang());
        return "success-ubah-barang";
    }
}