package apapedia.catalog.restcontroller;

import apapedia.catalog.model.Category;
import apapedia.catalog.restservice.CategoryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {
    @Autowired
    CategoryRestService categoryRestService;

    @GetMapping("/api/category/all-name")
    public ResponseEntity<?> getAllCategoryName() {
        try {
            return new ResponseEntity<>(categoryRestService.getAllCategoryName(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/category/all")
    public ResponseEntity<?> getAllCategory() {
        try {
            return new ResponseEntity<>(categoryRestService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
