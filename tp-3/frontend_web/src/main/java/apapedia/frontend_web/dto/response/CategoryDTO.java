package apapedia.frontend_web.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Getter
@Setter
public class CategoryDTO {
    @JsonProperty("idCategory")
    private UUID id;

    @JsonProperty("categoryName")
    private String name;
}
