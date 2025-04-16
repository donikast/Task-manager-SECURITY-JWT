package bg.tu_varna.sit.taskmanager16042025.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorDetail {
    private LocalDateTime time;
    private String message;
}