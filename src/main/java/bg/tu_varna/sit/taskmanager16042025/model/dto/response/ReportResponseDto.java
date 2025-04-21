package bg.tu_varna.sit.taskmanager16042025.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReportResponseDto {
    private long id;
    private String content;
    private LocalTime workTime;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}