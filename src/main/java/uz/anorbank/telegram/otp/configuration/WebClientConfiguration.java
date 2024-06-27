package uz.anorbank.telegram.otp.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({TelegramProperties.class})
public class WebClientConfiguration {
    private final TelegramProperties telegramProperties;


    @Bean
    public WebClient telegramWebClient() {
        String baseUrl = buildBaseUrl();

        log.info("telegramWebClient is configured with base url: {}", baseUrl);
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    private String buildBaseUrl() {
        return telegramProperties.getUrl() + "/bot" + telegramProperties.getToken();
    }
}
