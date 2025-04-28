package bg.tu_varna.sit.taskmanager16042025.service.impl;

import bg.tu_varna.sit.taskmanager16042025.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.FilterReportDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.ReportRequestDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.ReportResponseDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.TaskResponseDto;
import bg.tu_varna.sit.taskmanager16042025.model.entity.Report;
import bg.tu_varna.sit.taskmanager16042025.model.entity.Task;
import bg.tu_varna.sit.taskmanager16042025.repository.ReportRepository;
import bg.tu_varna.sit.taskmanager16042025.repository.TaskRepository;
import bg.tu_varna.sit.taskmanager16042025.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private TaskRepository taskRepository;
    private ReportRepository reportRepository;
    private ModelMapper mapper;

    public ReportServiceImpl(TaskRepository taskRepository, ReportRepository reportRepository, ModelMapper mapper) {
        this.taskRepository = taskRepository;
        this.reportRepository = reportRepository;
        this.mapper = mapper;
    }

    @Override
    public ReportResponseDto create(Long taskId, ReportRequestDto dto) throws ResourceNotFoundException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException(taskId, Task.class));

        Report report = mapper.map(dto, Report.class);
        report.setTask(task);
        Report created = reportRepository.save(report);
        return mapper.map(created, ReportResponseDto.class);
    }

    @Override
    public List<ReportResponseDto> getByTaskId(long id) throws ResourceNotFoundException {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Task.class));
        List<Report> reports = reportRepository.findByTaskId(id);
        return reports.stream()
                .map(report -> mapper.map(report, ReportResponseDto.class))
                .toList();
    }

    @Override
    public ReportResponseDto getById(long id) throws ResourceNotFoundException {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Report.class));
        return mapper.map(report, ReportResponseDto.class);
    }

    @Override
    public ReportResponseDto update(Long id, ReportRequestDto dto) throws ResourceNotFoundException {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Report.class));
        mapper.map(dto, report);
        Report updated = reportRepository.save(report);
        return mapper.map(updated, ReportResponseDto.class);
    }

    @Override
    public ReportResponseDto delete(long id) throws ResourceNotFoundException {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, Report.class));
        reportRepository.delete(report);
        return mapper.map(report, ReportResponseDto.class);
    }

    @Override
    public List<ReportResponseDto> getByWorkTimeInDateInterval(long taskId, FilterReportDto dto) {

        List<Report> reports = reportRepository.findAllByTaskIdAndWorkTimeAndDateCreatedBetween(taskId,
                dto.getTime(), dto.getFrom(), dto.getTo());
        return reports.stream()
                .map(report -> mapper.map(report, ReportResponseDto.class))
                .toList();
    }

}
