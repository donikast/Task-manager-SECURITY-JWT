package bg.tu_varna.sit.taskmanager16042025.repository;

import bg.tu_varna.sit.taskmanager16042025.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}
