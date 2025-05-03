package bg.tu_varna.sit.taskmanager16042025.service;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager16042025.model.Message;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.LoginDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.RegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    Message register(RegisterDto registerDto) throws TaskApiException;
    String login(LoginDto loginDto);
    Message logout(HttpServletRequest req);

}
