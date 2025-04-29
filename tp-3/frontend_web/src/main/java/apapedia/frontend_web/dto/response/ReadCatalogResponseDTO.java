package apapedia.frontend_web.dto.response;

import apapedia.frontend_web.dto.request.CreateCatalogRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadCatalogResponseDTO extends CreateCatalogRequestDTO {
    @NotNull
    private UUID id;
}
