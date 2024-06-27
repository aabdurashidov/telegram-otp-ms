package uz.anorbank.telegram.otp.client.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SetWebhook {
    @NotBlank
    private String url;
    private Integer max_connections;
    private List<String> allowed_updates;
}
