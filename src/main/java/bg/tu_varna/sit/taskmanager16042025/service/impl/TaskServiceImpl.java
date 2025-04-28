package bg.tu_varna.sit.taskmanager16042025.service.impl;

import bg.tu_varna.sit.taskmanager16042025.exception.RelatedEntityException;
import bg.tu_varna.sit.taskmanager16042025.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.TaskRequestDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.TaskResponseDto;
import bg.tu_varna.sit.taskmanager16042025.model.entity.Task;
import bg.tu_varna.sit.taskmanager16042025.repository.TaskRepository;
import bg.tu_varna.sit.taskmanager16042025.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

import static org.hibernate.validator.internal.util.ReflectionHelper.typeOf;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private ModelMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public TaskResponseDto create(TaskRequestDto dto) {
        Task task = mapper.map(dto, Task.class);
        Task addedTask = taskRepository.save(task);
        return mapper.map(addedTask, TaskResponseDto.class);
    }

    @Override
    public List<TaskResponseDto> findAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDto> taskResponseDtos = tasks
                        .stream()
                        .map(task -> mapper.map(task, TaskResponseDto.class))
                        .toList();
        return taskResponseDtos;
    }

    @Override
    public TaskResponseDto get(Long id) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Task.class));
        return mapper.map(task, TaskResponseDto.class);
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto dto) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Task.class));
        mapper.map(dto, task);
        Task updatedTask = taskRepository.save(task);
        return mapper.map(updatedTask, TaskResponseDto.class);
    }

    @Override
    public TaskResponseDto delete(Long id) throws RelatedEntityException, ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Task.class));
        try {
            taskRepository.delete(task);
        }
        catch (DataIntegrityViolationException e) {
            throw new RelatedEntityException(id, Task.class);
        }
        return mapper.map(task, TaskResponseDto.class);
    }
}
