package apapedia.frontend_web.service;


import java.util.List;
import java.util.Map;

import apapedia.frontend_web.dto.request.UpdateCatalogRequestDTO;
import apapedia.frontend_web.model.Catalog;

public interface CatalogService {
    Catalog saveCatalog(Catalog catalog);

    long jumlah();

    List<Catalog> getAllCatalog();

    Map<Catalog, Long> getAllBarangWithStok();

    void updatecatalog(UpdateCatalogRequestDTO catalogRequestDTO);
    
}
