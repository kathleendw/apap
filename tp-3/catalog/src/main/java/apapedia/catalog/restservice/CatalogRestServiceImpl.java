package apapedia.catalog.restservice;

import apapedia.catalog.model.Catalog;
import apapedia.catalog.repository.CatalogDb;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import apapedia.catalog.rest.Setting;
import jakarta.transaction.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@Transactional
public class CatalogRestServiceImpl implements CatalogRestService{
    @Autowired
    CatalogDb catalogDb;


    @Override
    public List<Catalog> retrieveRestAllCatalog(){
        return catalogDb.findAllByOrderByProductNameAsc();
    }

    @Override
    public List<Catalog> retrieveRestAllCatalogBySellerId(UUID sellerId) {
        return catalogDb.findAllBySellerIdOrderByProductNameAsc(sellerId);
    }

    @Override
    public List<Catalog> retrieveRestAllCatalogContaining(String search) {
        return catalogDb.findAllByProductNameContainingIgnoreCaseOrderByProductNameAsc(search);
    }

    @Override
    public List<Catalog> retrieveRestAllCatalogPrice(BigDecimal min, BigDecimal max) {
        return catalogDb.findByPriceBetweenOrderByProductNameAsc(min, max);
    }

    @Override
    public List<Catalog> retrieveRestAllCatalogContainingAndPrice(String search, BigDecimal min, BigDecimal max) {
        return catalogDb.findByPriceBetweenAndProductNameContainingIgnoreCaseOrderByProductNameAsc(min, max, search);
    }

    @Override
    public Optional<Catalog> retrieveRestCatalogById(UUID id) {
        return catalogDb.findById(id);
    }

    @Override
    public List<Catalog> retrieveRestAllSellerCatalogContaining(UUID id, String search) {
        return catalogDb.findAllBySellerIdAndProductNameContainingIgnoreCaseOrderByProductNameAsc(id, search);
    }

    @Override
    public List<Catalog> retrieveRestAllSellerCatalogPrice(UUID id, BigDecimal min, BigDecimal max) {
        return catalogDb.findAllBySellerIdAndPriceBetweenOrderByProductNameAsc(id, min, max);
    }

    @Override
    public List<Catalog> retrieveRestAllSellerCatalogContainingAndPrice(UUID id, String search, BigDecimal min, BigDecimal max) {
        return catalogDb.findBySellerIdAndPriceBetweenAndProductNameContainingIgnoreCaseOrderByProductNameAsc(id, min, max, search);
    }

    @Override
    public Catalog createCatalog(Catalog catalog) {
        return catalogDb.save(catalog);
    }

    @Override
    public List<Catalog> getAll() {
        return catalogDb.findAllByOrderByProductNameAsc();
    }

    @Override
    public Catalog getCatalogById(UUID id) {
        var catalog = catalogDb.findById(id);
        if (catalog.isPresent() && !catalog.get().isDeleted()) return catalog.get();
        else throw new EntityNotFoundException("Catalog not found");
    }

    @Override
    public Catalog updateCatalog(Catalog catalog) {
        var oldCatalog = getCatalogById(catalog.getId());
        oldCatalog.setStock(catalog.getStock());
        oldCatalog.setImagePath(catalog.getImagePath());
        oldCatalog.setProductName(catalog.getProductName());
        oldCatalog.setProductDescription(catalog.getProductDescription());
        oldCatalog.setPrice(catalog.getPrice());
        oldCatalog.setCategory(catalog.getCategory());
        return catalogDb.save(oldCatalog);
    }

    @Override
    public void deleteCatalog(UUID id) {
        var catalog = getCatalogById(id);
        catalog.setDeleted(true);
        catalogDb.save(catalog);
    }

    @Override
    public List<Catalog> getSortedCatalog(String sortBy, String sortMethod) {
        if (sortBy.equals("name")) {
            return sortMethod.toLowerCase().equals("desc") ? catalogDb.findAllByOrderByProductNameDesc() : catalogDb.findAllByOrderByProductNameAsc();
        } else if (sortBy.equals("price")) {
            return sortMethod.toLowerCase().equals("desc") ? catalogDb.findAllByOrderByPriceDesc() : catalogDb.findAllByOrderByPriceAsc();
        } else {
            throw new RestClientException("Invalid attribute to sort");
        }
    }
}
