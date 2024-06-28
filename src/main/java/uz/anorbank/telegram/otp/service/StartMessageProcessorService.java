package uz.anorbank.telegram.otp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import uz.anorbank.telegram.otp.client.TelegramClient;
import uz.anorbank.telegram.otp.client.request.SendMessage;
import uz.anorbank.telegram.otp.client.response.TelegramUpdate;
import uz.anorbank.telegram.otp.entity.User;
import uz.anorbank.telegram.otp.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class StartMessageProcessorService extends AbstractUpdateProcessorService {
    private final ReactiveStringRedisTemplate redisTemplate;
    private final TelegramClient telegramClient;
    private final UserRepository userRepository;

    @Override
    public void process(TelegramUpdate telegramUpdate) {
        Long chatId = telegramUpdate.getMessage().getChat().getId();
        String text = telegramUpdate.getMessage().getText();

        String template = text.split(" ")[1];

        String[] phoneNumberAndCode = template.split("C");
        String phoneNumber = phoneNumberAndCode[0];
        String tempCode = phoneNumberAndCode[1];

        log.info("chatId: {}, phoneNumber: {}", chatId, phoneNumber);

        redisTemplate.opsForValue().get(phoneNumber)
                .filter(code -> code.equals(tempCode))
                .switchIfEmpty(Mono.error(new Exception("You need to login with anorbank application")))
                .flatMap(code -> telegramClient.sendMessage(new SendMessage(chatId, "You have linked your phone number into telegram")))
                .flatMap(code -> userRepository.save(new User(phoneNumber, chatId)))
                .subscribe();

    }

    @Override
    public boolean supports(TelegramUpdate telegramUpdate) {
        boolean supports = telegramUpdate.getMessage() != null && telegramUpdate.getMessage().getText().startsWith("/start");
        if (supports) {
            log.info("{} supports this update", this.getClass().getSimpleName());
        }
        return supports;
    }
}
