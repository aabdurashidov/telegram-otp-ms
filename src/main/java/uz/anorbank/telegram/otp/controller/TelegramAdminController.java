package uz.anorbank.telegram.otp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import uz.anorbank.telegram.otp.client.request.SetWebhook;
import uz.anorbank.telegram.otp.client.response.BaseResponse;
import uz.anorbank.telegram.otp.client.response.GetWebhookInfoResponse;
import uz.anorbank.telegram.otp.service.TelegramAdminService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/telegram/admin")
public class TelegramAdminController {
    private final TelegramAdminService service;

    @PostMapping("/set-webhook")
    public Mono<BaseResponse<Boolean>> setWebhook(@Valid @RequestBody SetWebhook setWebhook) {
        log.info("POST: setWebhook: {}", setWebhook);
        return service.setWebhook(setWebhook);
    }

    @GetMapping("/delete-webhook")
    public Mono<BaseResponse<Boolean>> deleteWebhook() {
        log.info("POST: deleteWebhook");
        return service.deleteWebhook();
    }

    @GetMapping("/webhook-info")
    public Mono<BaseResponse<GetWebhookInfoResponse>> webhookInfo() {
        log.info("POST: webhookInfo");
        return service.getWebhookInfo();
    }
}
