package uz.anorbank.telegram.otp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("\"user\"")
public class User {
    @Id
    private String phoneNumber;
    private Long chatId;
}
