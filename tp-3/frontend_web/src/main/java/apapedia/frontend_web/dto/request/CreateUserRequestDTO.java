package apapedia.frontend_web.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequestDTO {
    @NotBlank(message = "Nama tidak boleh kosong!")
    private String nameUser;

    @NotBlank(message = "Username tidak boleh kosong!")
    private String username;

    @NotBlank(message = "Password tidak boleh kosong!")
    private String password;

    @NotBlank(message = "Email tidak boleh kosong!")
    private String email;

    @NotBlank(message = "category")
    private String category;

    private long balance;

    @NotBlank(message = "Alamat tidak boleh kosong!")
    private String address;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
