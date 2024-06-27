package uz.anorbank.telegram.otp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import uz.anorbank.telegram.otp.controller.dto.RegistrationRequestDto;
import uz.anorbank.telegram.otp.controller.dto.RegistrationResponseDto;
import uz.anorbank.telegram.otp.service.RegistrationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegistrationController {
    private final RegistrationService service;

    @PostMapping
    public Mono<RegistrationResponseDto> register(@Valid @RequestBody RegistrationRequestDto request) {
        return service.register(request);
    }
}
