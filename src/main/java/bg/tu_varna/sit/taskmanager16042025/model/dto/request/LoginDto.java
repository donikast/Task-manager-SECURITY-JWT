package bg.tu_varna.sit.taskmanager16042025.model.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {
    private String userName;
    private String password;
}
