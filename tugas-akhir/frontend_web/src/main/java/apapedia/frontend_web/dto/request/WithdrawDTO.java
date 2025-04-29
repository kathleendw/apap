package apapedia.frontend_web.dto.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawDTO {
    private UUID idUser;
    private long balance;
    private long withdrawal;
    private boolean withdrawn;
}
