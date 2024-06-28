package uz.anorbank.telegram.otp.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessage {
    private Long chat_id;
    private String message;
}
