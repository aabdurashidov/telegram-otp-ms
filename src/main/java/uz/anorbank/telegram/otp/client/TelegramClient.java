package uz.anorbank.telegram.otp.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uz.anorbank.telegram.otp.client.request.SetWebhook;
import uz.anorbank.telegram.otp.client.response.BaseResponse;
import uz.anorbank.telegram.otp.client.response.GetWebhookInfoResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramClient {
    private final WebClient telegramWebClient;

    public Mono<BaseResponse<Boolean>> setWebhook(SetWebhook setWebhook) {
        String path = "/setWebhook";
        return sendRequest(path, new ParameterizedTypeReference<>() {
        }, setWebhook);
    }

    public Mono<BaseResponse<Boolean>> deleteWebhook() {
        String path = "/deleteWebhook";
        return sendRequest(path, new ParameterizedTypeReference<>() {
        });
    }

    public Mono<BaseResponse<GetWebhookInfoResponse>> getWebhookInfo() {
        String path = "/getWebhookInfo";
        return sendRequest(path, new ParameterizedTypeReference<>() {
        });
    }

    private <REQUEST, RESPONSE> Mono<RESPONSE> sendRequest(String path, ParameterizedTypeReference<RESPONSE> responseClass, REQUEST body) {
        log.info("sending request to telegram. path: {}, body: {}", path, body);

        return telegramWebClient.post()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseClass);
    }

    private <RESPONSE> Mono<RESPONSE> sendRequest(String path, ParameterizedTypeReference<RESPONSE> responseClass) {
        log.info("sending request to telegram. path: {}", path);

        return telegramWebClient.post()
                .uri(path)
                .retrieve()
                .bodyToMono(responseClass);
    }
}
