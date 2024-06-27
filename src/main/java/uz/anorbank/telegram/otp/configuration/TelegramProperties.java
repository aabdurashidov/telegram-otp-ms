package uz.anorbank.telegram.otp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "application.telegram")
public class TelegramProperties {
    private String url;
    private String token;
}
