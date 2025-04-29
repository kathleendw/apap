package apap.tutorial.bacabaca.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class TranslateBukuResponseDTO {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private Data data;

    public static class Data {
        @JsonProperty("translatedText")
        private String translatedText;

        public String getTranslatedText(){
            return translatedText;
        }
      
    }
}
