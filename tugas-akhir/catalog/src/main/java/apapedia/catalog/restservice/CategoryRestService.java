package apapedia.catalog.restservice;

import apapedia.catalog.model.Category;

import java.util.List;

public interface CategoryRestService {
    void createCategories(List<Category> categories);

    List<String> getAllCategoryName();

    List<Category> getAll();
}
