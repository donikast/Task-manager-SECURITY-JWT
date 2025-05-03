package bg.tu_varna.sit.taskmanager16042025.model.dto.request;

import bg.tu_varna.sit.taskmanager16042025.model.entity.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RegisterDto {
    private String name;
    private String userName;
    private String password;
    private String email;
    private String role;
}
