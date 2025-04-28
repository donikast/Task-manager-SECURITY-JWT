package bg.tu_varna.sit.taskmanager16042025.service;

import bg.tu_varna.sit.taskmanager16042025.exception.RelatedEntityException;
import bg.tu_varna.sit.taskmanager16042025.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.TaskRequestDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.TaskResponseDto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TaskService {
    TaskResponseDto create(TaskRequestDto dto);

    List<TaskResponseDto> findAll();

    TaskResponseDto get(Long id) throws ResourceNotFoundException;

    TaskResponseDto update(Long id, TaskRequestDto dto) throws ResourceNotFoundException;

    TaskResponseDto delete(Long id) throws RelatedEntityException, ResourceNotFoundException;

}
