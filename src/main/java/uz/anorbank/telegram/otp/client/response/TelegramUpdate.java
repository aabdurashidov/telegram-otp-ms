package uz.anorbank.telegram.otp.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUpdate implements Serializable {
    private TelegramMessage message;
}
