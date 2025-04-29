package apapedia.catalog.dto.mapper;

import apapedia.catalog.dto.request.CreateCatalogRequestDTO;
import apapedia.catalog.dto.request.UpdateCatalogRequestDTO;
import apapedia.catalog.dto.response.CatalogResponseDTO;
import apapedia.catalog.model.Catalog;
import apapedia.catalog.repository.CategoryDb;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CatalogMapper {

    @Mapping(target = "category", ignore = true)
    Catalog createCatalogRequestDTOToCatalog(CreateCatalogRequestDTO createCatalogRequestDTO, @Context CategoryDb categoryDb);

    @AfterMapping
    default void fillCategory(@MappingTarget Catalog catalog, CreateCatalogRequestDTO catalogRequestDTO, @Context CategoryDb categoryDb){
        catalog.setCategory(categoryDb.findByIdCategory(catalogRequestDTO.getCategory()));
    }

    @Mapping(target = "category", ignore = true)
    CatalogResponseDTO catalogToCatalogResponseDTO(Catalog catalog);

    @AfterMapping
    default void fillCategoryResponse(@MappingTarget CatalogResponseDTO catalogResponseDTO, Catalog catalog){
        catalogResponseDTO.setCategory(catalog.getCategory().getIdCategory());
    }

    @Mapping(target = "deleted", ignore = true)
    Catalog updateCatalogRequestDTOToCatalog(UpdateCatalogRequestDTO updateCatalogRequestDTO);
}
