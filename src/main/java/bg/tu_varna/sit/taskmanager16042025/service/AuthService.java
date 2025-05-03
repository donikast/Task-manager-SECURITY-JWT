package bg.tu_varna.sit.taskmanager16042025.service;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager16042025.model.Message;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.RegisterDto;

public interface AuthService {
    Message register(RegisterDto registerDto) throws TaskApiException;
}
