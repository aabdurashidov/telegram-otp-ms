package uz.anorbank.telegram.otp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import uz.anorbank.telegram.otp.client.TelegramClient;
import uz.anorbank.telegram.otp.client.request.SetWebhook;
import uz.anorbank.telegram.otp.client.response.BaseResponse;
import uz.anorbank.telegram.otp.client.response.GetWebhookInfoResponse;

@Service
@RequiredArgsConstructor
public class TelegramAdminService {
    private final TelegramClient telegramClient;

    public Mono<BaseResponse<Boolean>> setWebhook(SetWebhook setWebhook) {
        return telegramClient.setWebhook(setWebhook);
    }

    public Mono<BaseResponse<Boolean>> deleteWebhook() {
        return telegramClient.deleteWebhook();
    }

    public Mono<BaseResponse<GetWebhookInfoResponse>> getWebhookInfo() {
        return telegramClient.getWebhookInfo();
    }
}
