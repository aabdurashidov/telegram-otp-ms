package uz.anorbank.telegram.otp.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramChat {
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
}
