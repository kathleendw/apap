package apap.tutorial.bacabaca.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TranslateBukuRequestDTO {
    private String sourceLanguage;
    private String targetLanguage;
    private UUID bookId;
    
}
