package apapedia.catalog;

import apapedia.catalog.model.Category;
import apapedia.catalog.restservice.CategoryRestService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(CategoryRestService categoryRestService) {
		return args -> {
			for (int i = 0; i < 0; i++) {

				List<String> categoriesName = new ArrayList<>(List.of("Aksesoris Fashion", "Buku & Alat Tulis", "Elektronik",
						"Fashion Bayi & Anak", "Fashion Muslim", "Fotografi", "Hobi & Koleksi",
						"Jam Tangan", "Perawatan & Kecantikan", "Makanan & Minuman", "Otomotif",
						"Perlengkapan Rumah", "Souvenir & Party Supplies"));

				List<Category> categories = new ArrayList<>();
				for (String categoryName : categoriesName) {
					var category = new Category();
					category.setCategoryName(categoryName);
					categories.add(category);
				}

				try {
					categoryRestService.createCategories(categories);
				} catch (Exception ignored) {
				}
			}
		};
	}

}
