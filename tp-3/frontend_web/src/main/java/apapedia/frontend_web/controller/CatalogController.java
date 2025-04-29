package apapedia.frontend_web.controller;

import apapedia.frontend_web.dto.request.CreateCatalogRequestDTO;
import apapedia.frontend_web.service.JwtService;
import apapedia.frontend_web.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;

import apapedia.frontend_web.dto.request.UpdateCatalogRequestDTO;
import apapedia.frontend_web.dto.response.ReadCatalogResponseDTO;
import apapedia.frontend_web.dto.response.CategoryDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
public class CatalogController {
    //TODO: change this
    private static final String URL_API_CATALOG = "http://localhost:8084";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private OrderService orderService;

    @GetMapping("/farelver")
    public String viewCatalogPageAll(Model model){
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<ReadCatalogResponseDTO>> catalogResponse = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (catalogResponse.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("catalogs", catalogResponse.getBody());
            } else {
                model.addAttribute("error", catalogResponse.getBody());
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "catalog/view-catalog-all";
    }

    @GetMapping("/catalog/farelver")
    public String viewCatalogPageFarel(Model model){
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<ReadCatalogResponseDTO>> catalogResponse = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (catalogResponse.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("catalogs", catalogResponse.getBody());
            } else {
                model.addAttribute("error", catalogResponse.getBody());
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "catalog/view-catalog";
    }
    
    @GetMapping("/catalog/tambah")
    public String formTambahCatalog(Model model) {
        var catalogDTO = new CreateCatalogRequestDTO();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<CategoryDTO>> categories = restTemplate.exchange(
                    URL_API_CATALOG + "/api/category/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (categories.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("categories", categories.getBody());
            } else {
                model.addAttribute("error", "Categories not found, try again later");
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("catalogDTO", catalogDTO);
        return "catalog/form-tambah-catalog";
    }

    @PostMapping("/catalog/tambah")
    public String tambahCatalog(@ModelAttribute CreateCatalogRequestDTO catalogDTO, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes, HttpSession httpSession) {
        String jwtToken = (String) httpSession.getAttribute("token");

        if (!imageFile.isEmpty()) {
            try {
                String filename = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
                String relativePath = "static/img/CatalogImage/" + filename;
                File directory = new File(resourceLoader.getResource("classpath:").getURI().getPath());
                String savePath = new File(directory, relativePath).getAbsolutePath();

                System.out.println("Saving to: " + savePath);

                imageFile.transferTo(new File(savePath));

                String retrievePath = "/img/CatalogImage/" + filename;
                catalogDTO.setImagePath(retrievePath);

            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to upload image");
                return "redirect:/catalog/tambah";
            }
        }

        catalogDTO.setSellerId(UUID.fromString(jwtService.getIdFromJwtToken(jwtToken)));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCatalogRequestDTO> requestEntity = new HttpEntity<>(catalogDTO, headers);

        ResponseEntity<ReadCatalogResponseDTO> newCatalog = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/create-catalog",
                    HttpMethod.POST,
                    requestEntity,
                    ReadCatalogResponseDTO.class
            );

        if (newCatalog.getStatusCode().is2xxSuccessful()) {
                redirectAttributes.addFlashAttribute("success", "Berhasil menambahkan catalog");
        }

        return "redirect:/catalog";
    }

    @GetMapping("/catalog/detail/{id}/farelver")
    public String detailCatalog(@PathVariable(value = "id") UUID catalogId, Model model){
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ReadCatalogResponseDTO> catalogResponse = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/" + catalogId,
                    HttpMethod.GET,
                    null,
                    ReadCatalogResponseDTO.class
            );

            if (catalogResponse.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("catalog", catalogResponse.getBody());
            } else {
                model.addAttribute("error", catalogResponse.getBody());
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "catalog/catalog-detail";
    }

    @GetMapping("/catalog/update/{id}")
    public String formUpdateCatalog(@PathVariable(value = "id") UUID catalogId, Model model) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UpdateCatalogRequestDTO> catalogResponse = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/detail/" + catalogId,
                    HttpMethod.GET,
                    null,
                    UpdateCatalogRequestDTO.class
            );

            if (catalogResponse.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("catalogDTO", catalogResponse.getBody());
            } else {
                model.addAttribute("error", catalogResponse.getBody());
            }

            ResponseEntity<List<CategoryDTO>> categories = restTemplate.exchange(
                    URL_API_CATALOG + "/api/category/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (categories.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("categories", categories.getBody());
            } else {
                model.addAttribute("error", "Categories not found, try again later");
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "catalog/form-ubah-catalog";
    }

    @PostMapping("/catalog/update")
    public String updateCatalog(@ModelAttribute UpdateCatalogRequestDTO catalogDTO, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCatalogRequestDTO> requestEntity = new HttpEntity(catalogDTO, headers);

        if (!imageFile.isEmpty()) {
            try {
                String filename = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
                String relativePath = "static/img/CatalogImage/" + filename;
                File directory = new File(resourceLoader.getResource("classpath:").getURI().getPath());
                String savePath = new File(directory, relativePath).getAbsolutePath();

                System.out.println("Saving to: " + savePath);

                imageFile.transferTo(new File(savePath));

                String retrievePath = "/img/CatalogImage/" + filename;
                catalogDTO.setImagePath(retrievePath);

            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "Failed to upload image");
                return "redirect:/catalog/update";
            }
        }


        try {
            ResponseEntity<ReadCatalogResponseDTO> updatedCatalog = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/update-catalog",
                    HttpMethod.PUT,
                    requestEntity,
                    ReadCatalogResponseDTO.class
            );

            if (updatedCatalog.getStatusCode().is2xxSuccessful()) {
                redirectAttributes.addFlashAttribute("success", "Berhasil meng-update catalog");
            } else {
                redirectAttributes.addFlashAttribute("error", updatedCatalog.getBody());
                return "redirect:/catalog/detail?id=" + catalogDTO.getId();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/catalog/detail?id=" + catalogDTO.getId();
        }

        return "redirect:/catalog/detail?id=" + catalogDTO.getId();
    }

    // @GetMapping({"/", "/catalog"})
    // public String viewCatalogPage(Model model, HttpSession httpSession) {
    //     String jwtToken = (String) httpSession.getAttribute("token");

    //     String url;

    //     if (jwtService.getRoleFromJwtToken(jwtToken).equals("SELLER")) {
    //         String sellerId = jwtService.getIdFromJwtToken(jwtToken);
    //         if (sellerId != null) {
    //             url = "http://localhost:8084/api/catalog/" + sellerId;
    //         } else {
    //             url = "http://localhost:8084/api/catalog/view-all";
    //         }
    //     } else {
    //         url = "http://localhost:8084/api/catalog/view-all";
    //     }

    //     System.out.println(url);

    //     List listCatalog = restTemplate.getForObject(url, List.class);

    //     String urlListCat = "http://localhost:8084/api/category/all-name";
    //     List listCategory = restTemplate.getForObject(urlListCat, List.class);

    //     model.addAttribute("listCatalog", listCatalog);
    //     model.addAttribute("listCategory", listCategory);

    //     return "view-catalog";
    // }

    // punya ayyubie
    @GetMapping("/catalog")
    public String viewCatalogPage(Model model, HttpSession httpSession) {
        String jwtToken = (String) httpSession.getAttribute("token");

        String url, urlGraph;
        String urlStat;
        Boolean isSeller;

        if (jwtService.getRoleFromJwtToken(jwtToken).equals("SELLER")) {
            String sellerId = jwtService.getIdFromJwtToken(jwtToken);
            String role = jwtService.getRoleFromJwtToken(jwtToken);
            if (sellerId != null && role.equals("SELLER")) {
                isSeller = true;
                model.addAttribute("isSeller", isSeller);

                url = "http://localhost:8084/api/catalog/" + sellerId;
                var listStats = orderService.getStats(sellerId);

                model.addAttribute("listStats", listStats);
                urlStat = "http://localhost:8083/api/order/seller/" + sellerId + "/stats";

                List listStat = restTemplate.getForObject(urlStat, List.class);
                if(!listStat.isEmpty()){
                    model.addAttribute("listStat", listStat);
                }
            } else {
                url = "http://localhost:8084/api/catalog/view-all";
            }
        } else {
            url = "http://localhost:8084/api/catalog/view-all";
        }

        System.out.println(url);

        List listCatalog = restTemplate.getForObject(url, List.class);

        String urlListCat = "http://localhost:8084/api/category/all-name";
        List listCategory = restTemplate.getForObject(urlListCat, List.class);

        model.addAttribute("listCatalog", listCatalog);
        model.addAttribute("listCategory", listCategory);

        return "view-catalog";
    }

    @GetMapping("/catalog/search")
    public String searchCatalogByName(@RequestParam("nama")String search, RedirectAttributes redirectAttrs, HttpSession httpSession) {
        String jwtToken = (String) httpSession.getAttribute("token");
        String url;

        if(search.equals("")){
            return "redirect:/catalog";
        }

        if (jwtService.validateJwtToken(jwtToken)) {
            String sellerId = jwtService.getIdFromJwtToken(jwtToken);
            String role = jwtService.getRoleFromJwtToken(jwtToken);
            if (sellerId != null && role.equals("SELLER")) {
                url = "http://localhost:8084/api/catalog/search/" +sellerId+ "/" + search;
            } else {
                url = "http://localhost:8084/api/catalog/search/" + search;
            }
        } else {
            url = "http://localhost:8084/api/catalog/search/" + search;
        }

        System.out.println(url);

        List listCatalog = restTemplate.getForObject(url, List.class);

        redirectAttrs.addFlashAttribute("searchTerm", search);
        redirectAttrs.addFlashAttribute("listCatalogSearch", listCatalog);

        return "redirect:/catalog";
    }

    @GetMapping("/catalog/price")
    public String searchCatalogByPrice(@RequestParam("min") BigDecimal min, @RequestParam("max")BigDecimal max, RedirectAttributes redirectAttrs, HttpSession httpSession) {
        String jwtToken = (String) httpSession.getAttribute("token");
        String url;

        if (jwtService.getRoleFromJwtToken(jwtToken).equals("SELLER")) {
            String sellerId = jwtService.getIdFromJwtToken(jwtToken);
            String role = jwtService.getRoleFromJwtToken(jwtToken);
            if (sellerId != null && role.equals("SELLER")) {
                url = "http://localhost:8084/api/catalog/price/" + sellerId + "/" + min + "/" + max;
            } else {
                url = "http://localhost:8084/api/catalog/price/" + min + "/" + max;
            }
        } else {
            url = "http://localhost:8084/api/catalog/price/" + min + "/" + max;
        }

        System.out.println(url);

        List listCatalog = restTemplate.getForObject(url, List.class);

        redirectAttrs.addFlashAttribute("listCatalogSearch", listCatalog);

        return "redirect:/catalog";
    }

    @GetMapping("/catalog/name/price")
    public String searchCatalogByNameAndPrice(
            @RequestParam(value = "nama", required = false) String search,
            @RequestParam(value = "min", required = false) BigDecimal min,
            @RequestParam(value = "max", required = false) BigDecimal max,
            RedirectAttributes redirectAttrs, HttpSession httpSession) {

        String jwtToken = (String) httpSession.getAttribute("token");
        String url;

        if (min == null) {
            min = BigDecimal.ZERO;
        }
        if (max == null) {
            max = new BigDecimal("999999999");
        }

        if (jwtService.getRoleFromJwtToken(jwtToken).equals("SELLER")) {
            String sellerId = jwtService.getIdFromJwtToken(jwtToken);
            String role = jwtService.getRoleFromJwtToken(jwtToken);
            if (sellerId != null && role.equals("SELLER")) {
                if(search.equals("")){
                    url = "http://localhost:8084/api/catalog/price/" + sellerId + "/" + min + "/" + max;
                } else {
                    url = "http://localhost:8084/api/catalog/search/" + sellerId + "/" + search + "/price/" + min + "/" + max;
                }
            } else {
                if(search.equals("")){
                    url = "http://localhost:8084/api/catalog/price/" + min + "/" + max;
                } else {
                    url = "http://localhost:8084/api/catalog/search/" + search + "/price/" + min + "/" + max;
                }
            }
        } else {
            if(search.equals("")){
                url = "http://localhost:8084/api/catalog/price/" + min + "/" + max;
            } else {
                url = "http://localhost:8084/api/catalog/search/" + search + "/price/" + min + "/" + max;
            }
        }

        System.out.println(url);

        List listCatalog = restTemplate.getForObject(url, List.class);
        redirectAttrs.addFlashAttribute("listCatalogSearch", listCatalog);
        redirectAttrs.addFlashAttribute("searchTerm", search);
        redirectAttrs.addFlashAttribute("searchMin", min);
        redirectAttrs.addFlashAttribute("searchMax", max);

        return "redirect:/catalog";
    }

    @GetMapping("/catalog/detail")
    public String viewDetailCatalog(Model model, @RequestParam("id") UUID id, HttpSession httpSession){
        String jwtToken = (String) httpSession.getAttribute("token");
        String role = jwtService.getRoleFromJwtToken(jwtToken);
        Boolean isSeller;
        Boolean isCustomer;
        if(role.equals("SELLER")){
            isSeller = true;
            model.addAttribute("isSeller", isSeller);
        } else if(role.equals("CUSTOMER")){
            isCustomer = true;
            model.addAttribute("isCustomer", isCustomer);
        }

        String url = "http://localhost:8084/api/catalog/detail/" + id.toString();
        Object catalog = restTemplate.getForObject(url, Object.class);
        model.addAttribute("catalog", catalog);
        return "catalog-detail";
    }

}

