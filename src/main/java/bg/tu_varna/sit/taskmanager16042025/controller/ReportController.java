package bg.tu_varna.sit.taskmanager16042025.controller;

import bg.tu_varna.sit.taskmanager16042025.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.FilterReportDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.ReportRequestDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.ReportResponseDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.TaskResponseDto;
import bg.tu_varna.sit.taskmanager16042025.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated //Добавено в лабораторно упражнение 8
@RestController
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping("/task/{id}")
    public ResponseEntity<ReportResponseDto> create(@PathVariable(name = "id") long taskId,
                                                    @Valid @RequestBody ReportRequestDto dto) throws ResourceNotFoundException {
        ReportResponseDto created = reportService.create(taskId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<List<ReportResponseDto>> getAllByTask(@PathVariable(name = "id") long taskId) throws ResourceNotFoundException {
        List<ReportResponseDto> reports = reportService.getByTaskId(taskId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDto> get(@PathVariable Long id) throws ResourceNotFoundException {
        ReportResponseDto report = reportService.getById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/filter/{taskId}")
    public ResponseEntity<List<ReportResponseDto>> getByWorkTimeInDateInterval(
            @PathVariable long taskId,
            @Valid @RequestBody FilterReportDto filter
    ) throws ResourceNotFoundException {
        List<ReportResponseDto> reports = reportService.getByWorkTimeInDateInterval(taskId, filter);
        return ResponseEntity.ok(reports);

    }

    @GetMapping("/task/{id}/max-hours-worked")
    public ResponseEntity<List<ReportResponseDto>> getByTaskWithMaxHoursWorked(@PathVariable(name = "id") long taskId) throws ResourceNotFoundException {
        throw new ResourceNotFoundException(taskId, TaskResponseDto.class);  //Добавено в лабораторно упражнение 8
        //return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ReportResponseDto> update(@PathVariable Long id, @Valid @RequestBody ReportRequestDto dto) throws ResourceNotFoundException {
        ReportResponseDto updated = reportService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ReportResponseDto> delete(@PathVariable Long id) throws ResourceNotFoundException {
        ReportResponseDto deleted = reportService.delete(id);
        return ResponseEntity.ok(deleted);
    }
}