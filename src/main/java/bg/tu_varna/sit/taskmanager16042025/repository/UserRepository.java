package bg.tu_varna.sit.taskmanager16042025.repository;

import bg.tu_varna.sit.taskmanager16042025.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    boolean existsByUserName(String username);
}
