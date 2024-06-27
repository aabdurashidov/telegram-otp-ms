package uz.anorbank.telegram.otp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "application.telegram")
public class TelegramProperties {
    private String url;
    private String token;
    private Boolean enableSetWebhookOnStartup;
    private String exposedUrl;
    private Integer maxConnections;
    private List<String> allowedUpdates;
    private String telegramBotUsername;
}
