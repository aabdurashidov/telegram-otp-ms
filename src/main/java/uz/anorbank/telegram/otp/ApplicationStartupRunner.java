package uz.anorbank.telegram.otp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;
import uz.anorbank.telegram.otp.client.TelegramClient;
import uz.anorbank.telegram.otp.client.request.SetWebhook;
import uz.anorbank.telegram.otp.configuration.TelegramProperties;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationStartupRunner implements CommandLineRunner {
    private final TelegramProperties telegramProperties;
    private final TelegramClient telegramClient;
    private final ApplicationContext applicationContext;

    @Override
    public void run(String... args) {
        SetWebhook setWebhookRequest = new SetWebhook()
                .setUrl(telegramProperties.getExposedUrl())
                .setMax_connections(telegramProperties.getMaxConnections())
                .setAllowed_updates(telegramProperties.getAllowedUpdates());

        if (telegramProperties.getEnableSetWebhookOnStartup()) {
            log.info("application.telegram.enable-set-webhook-on-startup is enabled, trying to set webhook");
            telegramClient.setWebhook(setWebhookRequest)
                    .doOnNext(response -> {
                        if (response.getOk()) {
                            log.info("new webhook registered successfully: {}", setWebhookRequest);
                        } else {
                            log.error("Unable to unable to register webhook: {}", response);
                            shutdownApplication();
                        }
                    })
                    .doOnError(exception -> {
                        log.error("Unable to unable to register webhook", exception);
                        shutdownApplication();
                    })
                    .subscribe();
        } else {
            log.info("application.telegram.enable-set-webhook-on-startup is disabled");
        }
    }

    private void shutdownApplication() {
        Schedulers.boundedElastic().schedule(() -> {
            SpringApplication.exit(applicationContext, () -> 1);
        });
    }
}
