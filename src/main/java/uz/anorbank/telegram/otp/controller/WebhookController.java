package uz.anorbank.telegram.otp.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.anorbank.telegram.otp.client.response.TelegramUpdate;
import uz.anorbank.telegram.otp.service.AbstractUpdateProcessorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/telegram/webhook")
public class WebhookController {
    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
    private final List<AbstractUpdateProcessorService> updateProcessorServices;

    @PostMapping
    public void handleTelegramUpdates(@RequestBody TelegramUpdate update) {
        log.info("Telegram update received: {}", update);
        updateProcessorServices.stream()
                .filter(processor -> processor.supports(update))
                .forEach(processor -> processor.process(update));
    }
}
