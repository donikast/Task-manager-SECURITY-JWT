package bg.tu_varna.sit.taskmanager16042025.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(mappedBy="roles", fetch = FetchType.EAGER)
    private Set<User> users;
}

/*id, наименование.*/
