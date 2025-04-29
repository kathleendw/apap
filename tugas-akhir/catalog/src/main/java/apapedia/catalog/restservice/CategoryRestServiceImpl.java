package apapedia.catalog.restservice;

import apapedia.catalog.model.Category;
import apapedia.catalog.repository.CategoryDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryRestServiceImpl implements CategoryRestService{
    @Autowired
    CategoryDb categoryDb;

    @Override
    public void createCategories(List<Category> categories) {
        categoryDb.saveAll(categories);
    }

    @Override
    public List<String> getAllCategoryName() {
        return categoryDb.findAllDistinct();
    }

    @Override
    public List<Category> getAll() {
        return categoryDb.findAll();
    }
}
