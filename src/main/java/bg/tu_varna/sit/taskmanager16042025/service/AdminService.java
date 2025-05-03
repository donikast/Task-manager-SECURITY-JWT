package bg.tu_varna.sit.taskmanager16042025.service;

import bg.tu_varna.sit.taskmanager16042025.exception.TaskApiException;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.RoleDto;
import bg.tu_varna.sit.taskmanager16042025.model.Message;

public interface AdminService {
    Message createRole(RoleDto roleDto) throws TaskApiException;
}
