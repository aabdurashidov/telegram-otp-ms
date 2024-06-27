package uz.anorbank.telegram.otp.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetWebhookInfoResponse {
    private String url;
    private Boolean has_custom_certificate;
    private Integer pending_update_count;
    private String ip_address;
    private String last_error_message;
    private Integer last_error_date;
    private Integer last_synchronization_error_date;
    private Integer max_connections;
    private List<String> allowed_updates;
}
