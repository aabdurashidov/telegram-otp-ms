package uz.anorbank.telegram.otp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import uz.anorbank.telegram.otp.configuration.TelegramProperties;
import uz.anorbank.telegram.otp.controller.dto.RegistrationRequestDto;
import uz.anorbank.telegram.otp.controller.dto.RegistrationResponseDto;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final TemporaryCodeService temporaryCodeService;
    private final TelegramProperties telegramProperties;
    private static final String DIVIDER = "%s|%s";
    private static final String DEEPLINK = "https://t.me/%s?start=%s";

    public Mono<RegistrationResponseDto> register(RegistrationRequestDto request) {
        String phoneNumber = request.getPhoneNumber();

        return temporaryCodeService.generate(phoneNumber)
                .map(code -> buildResponse(code, phoneNumber)
                );
    }

    private RegistrationResponseDto buildResponse(UUID code, String phoneNumber) {
        return new RegistrationResponseDto(DEEPLINK.formatted(telegramProperties.getTelegramBotUsername(),
                DIVIDER.formatted(phoneNumber, code)));
    }
}
