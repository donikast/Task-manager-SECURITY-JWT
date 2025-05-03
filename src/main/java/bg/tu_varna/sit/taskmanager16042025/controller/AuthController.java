package bg.tu_varna.sit.taskmanager16042025.controller;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager16042025.model.Message;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.RegisterDto;
import bg.tu_varna.sit.taskmanager16042025.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody RegisterDto registerDto) throws TaskApiException {
      Message message = authService.register(registerDto);
      return ResponseEntity.status(201).body(message);
    }

}
