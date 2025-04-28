package bg.tu_varna.sit.taskmanager16042025.service;

import bg.tu_varna.sit.taskmanager16042025.exception.ResourceNotFoundException;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.FilterReportDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.request.ReportRequestDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.ReportResponseDto;
import bg.tu_varna.sit.taskmanager16042025.model.dto.response.TaskResponseDto;
import bg.tu_varna.sit.taskmanager16042025.model.entity.Report;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReportService {

    ReportResponseDto create(Long taskId, ReportRequestDto dto) throws ResourceNotFoundException;

    List<ReportResponseDto> getByTaskId(long id) throws ResourceNotFoundException;

    ReportResponseDto getById(long id) throws ResourceNotFoundException;

    ReportResponseDto update(Long id, ReportRequestDto dto) throws ResourceNotFoundException;

    ReportResponseDto delete(long id) throws ResourceNotFoundException;

    List<ReportResponseDto> getByWorkTimeInDateInterval(
            long taskId, FilterReportDto dto);

}
