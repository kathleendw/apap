package apapedia.catalog.restservice;

import apapedia.catalog.model.Catalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogRestService {
    Catalog createCatalog(Catalog catalog);

    Catalog updateCatalog(Catalog catalog);

    Catalog getCatalogById(UUID id);

    List<Catalog> getAll();

    void deleteCatalog(UUID id);

    List<Catalog> getSortedCatalog(String sortBy, String sortMethod);

    List<Catalog> retrieveRestAllCatalog();

    List<Catalog> retrieveRestAllCatalogBySellerId(UUID sellerId);

    List<Catalog> retrieveRestAllCatalogContaining(String search);

    List<Catalog> retrieveRestAllCatalogPrice(BigDecimal min, BigDecimal max);

    List<Catalog> retrieveRestAllCatalogContainingAndPrice(String search, BigDecimal min, BigDecimal max);

    Optional<Catalog> retrieveRestCatalogById(UUID id);

    List<Catalog> retrieveRestAllSellerCatalogContaining(UUID id, String search);

    List<Catalog> retrieveRestAllSellerCatalogPrice(UUID id, BigDecimal min, BigDecimal max);

    List<Catalog> retrieveRestAllSellerCatalogContainingAndPrice(UUID id, String search, BigDecimal min, BigDecimal max);

}

