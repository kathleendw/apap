package apap.tutorial.bacabaca.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailedResponseDTO {
    private Boolean status;
    private String message;
}
