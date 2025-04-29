package apap.ti.silogistik2106637366.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import apap.ti.silogistik2106637366.DTO.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106637366.DTO.response.ReadPermintaanPengirimanResponseDTO;
import apap.ti.silogistik2106637366.DTO.PermintaanPengirimanMapper;
import apap.ti.silogistik2106637366.service.PermintaanPengirimanService;
import apap.ti.silogistik2106637366.service.KaryawanService;
import apap.ti.silogistik2106637366.service.BarangService;
import apap.ti.silogistik2106637366.model.PermintaanPengiriman;
import apap.ti.silogistik2106637366.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106637366.model.Barang;

@Controller
public class PermintaanPengirimanController {

    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    private KaryawanService karyawanService;

    @Autowired
    private BarangService barangService;

    @GetMapping("permintaan-pengiriman/tambah")
    public String formTambahPermintaanPengiriman(Model model) {
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        var listKaryawanExisting = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping("permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, Model model) {
        int totalKuantitas = 0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()) {
            totalKuantitas += permintaanPengirimanBarang.getKuantitas();
        }
        String jumlahBarang = "";
        if (totalKuantitas <= 99) {
            jumlahBarang = String.format("%02d", totalKuantitas);
        } else {
            String kuantitas = Integer.toString(totalKuantitas);
            jumlahBarang = kuantitas.substring(kuantitas.length() - 2);
        }
        String jenisLayanan = getJenisLayananByValue(permintaanPengirimanDTO.getJenisLayanan());
        
        var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);
        
        Date waktuPermintaan = new Date();
        permintaanPengiriman.setWaktuPermintaan(waktuPermintaan);

        SimpleDateFormat waktuPermintaanFormatter = new SimpleDateFormat("HH:mm:ss");
        String waktuPermintaanString = waktuPermintaanFormatter.format(permintaanPengiriman.getWaktuPermintaan());
        
        String nomorPengiriman = "REQ" + jumlahBarang + jenisLayanan + waktuPermintaanString;
        permintaanPengiriman.setNomorPengiriman(nomorPengiriman);
        
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()) {
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
        }
        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);
        model.addAttribute("idPermintaanPengiriman", permintaanPengiriman.getIdPermintaanPengiriman());
        return "success-tambah-permintaan-pengiriman";
    }

    private String getJenisLayananByValue(Integer value) {
        switch (value) {
            case 1:
                return "SAM";
            case 2:
                return "KIL";
            case 3:
                return "REG";
            case 4:
                return "HEM";
        }
        return "";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowTambahPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO, Model model) {
        if (permintaanPengirimanDTO.getListPermintaanPengirimanBarang() == null || permintaanPengirimanDTO.getListPermintaanPengirimanBarang().size() == 0) { 
            permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }
        permintaanPengirimanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        var listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);

        var listKaryawanExisting = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawanExisting", listKaryawanExisting);
        return "form-tambah-permintaan-pengiriman";
    } 

    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        List<PermintaanPengiriman> listPermintaanPengiriman = new ArrayList<>();
        for (PermintaanPengiriman permintaanPengiriman : permintaanPengirimanService.getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getIsCancelled() != null && permintaanPengiriman.getIsCancelled()) {
                continue;
            } else {
                listPermintaanPengiriman.add(permintaanPengiriman);
            }
        }
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);

        List<String> listWaktuPermintaan = new ArrayList<>();
        SimpleDateFormat waktuPermintaanFormatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
        List<String> listTanggalPengiriman = new ArrayList<>();
        SimpleDateFormat tanggalPengirimanFormatter = new SimpleDateFormat("dd-MM-yyyy");
        for (PermintaanPengiriman permintaanPengiriman : listPermintaanPengiriman) {
            String waktuPermintaanString = waktuPermintaanFormatter.format(permintaanPengiriman.getWaktuPermintaan());
            listWaktuPermintaan.add(waktuPermintaanString);
            String tanggalPengirimanString = tanggalPengirimanFormatter.format(permintaanPengiriman.getTanggalPengiriman());
            listTanggalPengiriman.add(tanggalPengirimanString);
        }
        model.addAttribute("listWaktuPermintaan", listWaktuPermintaan);
        model.addAttribute("listTanggalPengiriman", listTanggalPengiriman);
        return "viewall-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
        PermintaanPengiriman permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        ReadPermintaanPengirimanResponseDTO readPermintaanPengirimanResponseDTO = permintaanPengirimanMapper.permintaanPengirimanToReadPermintaanPengirimanResponseDTO(permintaanPengiriman);
        model.addAttribute("permintaanPengiriman", readPermintaanPengirimanResponseDTO);

        SimpleDateFormat waktuPermintaanFormatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
        String waktuPermintaanString = waktuPermintaanFormatter.format(permintaanPengiriman.getWaktuPermintaan());
        model.addAttribute("waktuPermintaan", waktuPermintaanString);

        SimpleDateFormat tanggalPengirimanFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String tanggalPengirimanString = tanggalPengirimanFormatter.format(permintaanPengiriman.getTanggalPengiriman());
        model.addAttribute("tanggalPengiriman", tanggalPengirimanString);

        List<Barang> listBarang = new ArrayList<>();
        List<Integer> listKuantitas = new ArrayList<>();
        List<Long> listTotalHarga = new ArrayList<>();
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListPermintaanPengirimanBarang()) {
            listBarang.add(permintaanPengirimanBarang.getBarang());
            listKuantitas.add(permintaanPengirimanBarang.getKuantitas());
            listTotalHarga.add(permintaanPengirimanBarang.getBarang().getHargaBarang() * permintaanPengirimanBarang.getKuantitas());
        }
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listKuantitas", listKuantitas);
        model.addAttribute("listTotalHarga", listTotalHarga);

        return "view-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/delete/{idPermintaanPengiriman}")
    public String cancelPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        permintaanPengirimanService.cancelPermintaanPengiriman(permintaanPengiriman);
        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());
        return "success-cancel-permintaan-pengiriman";
    }

}
