package uz.anorbank.telegram.otp.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramMessage {
    private Long message_id;
    private TelegramFrom from;
    private TelegramChat chat;
    private Long date;
    private String text;
}
