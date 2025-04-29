package apapedia.user.dto.response;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceResponse {
    private UUID idUser;
    private long money;
    private long balance;
    private boolean withdrawn;
}
