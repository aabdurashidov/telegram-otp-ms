package uz.anorbank.telegram.otp.service;

import uz.anorbank.telegram.otp.client.response.TelegramUpdate;

public abstract class AbstractUpdateProcessorService {
    public abstract void process(TelegramUpdate telegramUpdate);

    public abstract boolean supports(TelegramUpdate telegramUpdate);
}
