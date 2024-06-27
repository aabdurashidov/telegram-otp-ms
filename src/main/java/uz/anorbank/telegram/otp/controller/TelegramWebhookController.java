package uz.anorbank.telegram.otp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telegram/webhook")
public class TelegramWebhookController {
    @PostMapping
    public void handleTelegramUpdates(@RequestBody Object object) {
        System.out.println(object);
    }
}
