package apapedia.catalog.repository;

import apapedia.catalog.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogDb extends JpaRepository<Catalog, UUID> {
    List<Catalog> findAllByOrderByProductNameAsc();

    List<Catalog> findAllByOrderByProductNameDesc();

    List<Catalog> findAllByOrderByPriceAsc();

    List<Catalog> findAllByOrderByPriceDesc();

    List<Catalog> findAllBySellerIdOrderByProductNameAsc(UUID id);

    List<Catalog> findAllByProductNameContainingIgnoreCaseOrderByProductNameAsc(String search);

    List<Catalog> findByPriceBetweenOrderByProductNameAsc(BigDecimal min, BigDecimal max);

    List<Catalog> findByPriceBetweenAndProductNameContainingIgnoreCaseOrderByProductNameAsc(BigDecimal min, BigDecimal max, String search);

    List<Catalog> findAllBySellerIdAndProductNameContainingIgnoreCaseOrderByProductNameAsc(UUID id, String search);

    List<Catalog> findAllBySellerIdAndPriceBetweenOrderByProductNameAsc(UUID id, BigDecimal min, BigDecimal max);

    List<Catalog> findBySellerIdAndPriceBetweenAndProductNameContainingIgnoreCaseOrderByProductNameAsc(UUID id, BigDecimal min, BigDecimal max, String search);

}
