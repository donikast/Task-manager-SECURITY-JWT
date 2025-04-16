package bg.tu_varna.sit.taskmanager16042025.model.dto.request;


import bg.tu_varna.sit.taskmanager16042025.model.validator.AfterMidnight;
import bg.tu_varna.sit.taskmanager16042025.model.validator.ValidDateRange;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ValidDateRange
public class FilterReportDto {
    @NotNull(message = "Time is required")
    @AfterMidnight
    private LocalTime time;

    @NotNull(message = "Date from is required")
    private LocalDateTime from;

    @NotNull(message = "Date to is required")
    private LocalDateTime to;
}
