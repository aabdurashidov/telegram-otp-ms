package uz.anorbank.telegram.otp.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramFrom {
    private Long id;
    private boolean is_bot;
    private String first_name;
    private String last_name;
    private String username;
    private String language_code;
}
