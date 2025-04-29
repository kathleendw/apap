package apapedia.catalog.restcontroller;

import apapedia.catalog.dto.mapper.CatalogMapper;
import apapedia.catalog.dto.request.CreateCatalogRequestDTO;
import apapedia.catalog.dto.request.UpdateCatalogRequestDTO;
import apapedia.catalog.restservice.CatalogRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import apapedia.catalog.model.Catalog;
import apapedia.catalog.repository.CategoryDb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;


import java.util.UUID;

@RestController
public class CatalogRestController {
    @Autowired
    CatalogRestService catalogRestService;
    @Autowired
    CatalogMapper catalogMapper;
    @Autowired
    CategoryDb categoryDb;

    @PostMapping("/api/catalog/create-catalog")
    public ResponseEntity createCatalog(@Valid @RequestBody CreateCatalogRequestDTO catalogDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < bindingResult.getErrorCount(); i++) {
                res.append(bindingResult.getFieldErrors().get(i).getDefaultMessage()).append("/n");
            }
            return new ResponseEntity<>(res.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            var catalog = catalogMapper.createCatalogRequestDTOToCatalog(catalogDTO, categoryDb);
            catalogRestService.createCatalog(catalog);
            var catalogToDTO = catalogMapper.catalogToCatalogResponseDTO(catalog);
            return new ResponseEntity<>(catalogToDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/catalog/update-catalog")
    public ResponseEntity updateCatalog(@Valid @RequestBody UpdateCatalogRequestDTO catalogRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < bindingResult.getErrorCount(); i++) {
                res.append(bindingResult.getFieldErrors().get(i).getDefaultMessage()).append("/n");
            }
            return new ResponseEntity<>(res.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            var catalog = catalogMapper.updateCatalogRequestDTOToCatalog(catalogRequestDTO);
            return new ResponseEntity<>(catalogRestService.updateCatalog(catalog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/catalog/delete-catalog/{id}")
    public ResponseEntity deleteCatalog(@PathVariable("id") UUID idCatalog) {
        try {
            catalogRestService.deleteCatalog(idCatalog);
            return new ResponseEntity<>("Catalog has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/catalog/sort")
    public ResponseEntity sortCatalog(
            @RequestParam(name = "by", required = false, defaultValue = "name") String sortBy,
            @RequestParam(name = "method", required = false, defaultValue = "asc") String sortMethod) {

        try {
            return new ResponseEntity<>(catalogRestService.getSortedCatalog(sortBy, sortMethod), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/catalog/all")
    public ResponseEntity getAllSortedByName() {
        try {
            return new ResponseEntity<>(catalogRestService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/catalog/{id}")
    public ResponseEntity getCatalogByID(@PathVariable(value = "id") UUID idCatalog) {
        try {
            return new ResponseEntity<>(catalogRestService.getCatalogById(idCatalog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/catalog/view-all")
    private List<Catalog> retrieveAllCatalog(){
        return catalogRestService.retrieveRestAllCatalog();
    }

    @GetMapping(value = "/api/catalog-all/{sellerId}")
    private List<Catalog> retrieveAllCatalogBySellerId(@PathVariable("sellerId") String sellerId){
        try{
            return catalogRestService.retrieveRestAllCatalogBySellerId(UUID.fromString(sellerId));
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id Seller " + sellerId + " not found"
            );
        }
    }

    @GetMapping(value = "/api/catalog/search/{search}")
    private List<Catalog> retrieveAllCatalogByName(@PathVariable("search") String search){
        try{
            return catalogRestService.retrieveRestAllCatalogContaining(search);
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Catalog " + search + " not found"
            );
        }
    }

    @GetMapping(value = "/api/catalog/price/{min}/{max}")
    private List<Catalog> retrieveAllCatalogByPrice(@PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max){
        try{
            return catalogRestService.retrieveRestAllCatalogPrice(min, max);
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Catalog between price not found"
            );
        }
    }

    @GetMapping(value = "/api/catalog/search/{search}/price/{min}/{max}")
    private List<Catalog> retrieveAllCatalogByNameAndPrice(@PathVariable("search") String search, @PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max){
        try{
            return catalogRestService.retrieveRestAllCatalogContainingAndPrice(search, min, max);
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Catalog not found"
            );
        }
    }

    @GetMapping(value = "/api/catalog/detail/{id}")
    private Optional<Catalog> retrieveAllCatalogById(@PathVariable("id") String id){
        try{
            return catalogRestService.retrieveRestCatalogById(UUID.fromString(id));
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id " + id + " not found"
            );
        }
    }

    @GetMapping(value = "/api/catalog/search/{sellerId}/{search}")
    private List<Catalog> retrieveAllSellerCatalogByName(@PathVariable("search") String search, @PathVariable("sellerId") String sellerId){
        try{
            return catalogRestService.retrieveRestAllSellerCatalogContaining(UUID.fromString(sellerId), search);
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Catalog " + search + " not found"
            );
        }
    }

    @GetMapping(value = "/api/catalog/price/{sellerId}/{min}/{max}")
    private List<Catalog> retrieveAllSellerCatalogByPrice(@PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max, @PathVariable("sellerId") String sellerId){
        try{
            return catalogRestService.retrieveRestAllSellerCatalogPrice(UUID.fromString(sellerId), min, max);
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Catalog between price not found"
            );
        }
    }

    @GetMapping(value = "/api/catalog/search/{sellerId}/{search}/price/{min}/{max}")
    private List<Catalog> retrieveAllSellerCatalogByNameAndPrice(@PathVariable("search") String search, @PathVariable("min") BigDecimal min, @PathVariable("max") BigDecimal max, @PathVariable("sellerId") String sellerId){
        try{
            return catalogRestService.retrieveRestAllSellerCatalogContainingAndPrice(UUID.fromString(sellerId), search, min, max);
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Catalog not found"
            );
        }
    }

}
