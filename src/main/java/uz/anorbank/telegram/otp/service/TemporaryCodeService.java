package uz.anorbank.telegram.otp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemporaryCodeService {
    private final ReactiveStringRedisTemplate redisTemplate;

    public Mono<UUID> generate(String phoneNumber) {
        UUID tempCode = UUID.randomUUID();
        return redisTemplate.opsForValue()
                .set(phoneNumber, tempCode.toString(), Duration.of(1, ChronoUnit.MINUTES))
                .map(saved -> tempCode);
    }
}
