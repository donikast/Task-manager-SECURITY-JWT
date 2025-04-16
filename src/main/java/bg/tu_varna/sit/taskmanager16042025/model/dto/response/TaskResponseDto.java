package bg.tu_varna.sit.taskmanager16042025.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TaskResponseDto {
    private long id;
    private String summary;
    private String description;
    private LocalDateTime deadline;
    private Set<ReportResponseDto> reports;
}