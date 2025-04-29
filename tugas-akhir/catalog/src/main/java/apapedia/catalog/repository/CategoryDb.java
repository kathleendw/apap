package apapedia.catalog.repository;

import apapedia.catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface CategoryDb extends JpaRepository<Category, UUID> {
    Category findByIdCategory(UUID idCategory);

    @Query("SELECT DISTINCT c.categoryName FROM Category c ORDER BY c.categoryName")
    List<String> findAllDistinct();

}
