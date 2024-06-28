package uz.anorbank.telegram.otp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemporaryCodeService {
    private final ReactiveStringRedisTemplate redisTemplate;
    private final Random random = new Random();

    public Mono<Integer> generate(String phoneNumber) {
        int code = random.nextInt(100000, 10000000);

        return redisTemplate.opsForValue()
                .set(phoneNumber, String.valueOf(code), Duration.of(1, ChronoUnit.MINUTES))
                .map(saved -> code);
    }
}
