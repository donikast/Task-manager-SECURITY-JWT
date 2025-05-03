package bg.tu_varna.sit.taskmanager16042025.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
}
