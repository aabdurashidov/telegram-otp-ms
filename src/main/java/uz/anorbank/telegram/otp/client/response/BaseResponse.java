package uz.anorbank.telegram.otp.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<RESULT> {
    private Boolean ok;
    private RESULT result;
    private String description;
    private Integer error_code;
}
