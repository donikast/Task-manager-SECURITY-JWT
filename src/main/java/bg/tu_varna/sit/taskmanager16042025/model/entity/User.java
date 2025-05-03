package bg.tu_varna.sit.taskmanager16042025.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String userName;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles")
    private Set<Role> roles;
}
/*id, име, потребителско име, парола, електронна поща*/