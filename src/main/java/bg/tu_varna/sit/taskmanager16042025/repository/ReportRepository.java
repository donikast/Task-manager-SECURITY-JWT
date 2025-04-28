package bg.tu_varna.sit.taskmanager16042025.repository;

import bg.tu_varna.sit.taskmanager16042025.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByTaskId(long taskId);



    List<Report> findAllByTaskIdAndWorkTimeAndDateCreatedBetween(Long taskId,
                                                                 LocalTime time,
                                                                 LocalDateTime startDate,
                                                                 LocalDateTime  endDate);

    Report findFirstByTaskIdOrderByWorkTimeDesc(Long taskId);

    @Query("SELECT SUM(r.workTime) FROM Report r WHERE r.task.id = :taskId")
    Integer getTotalMinutesByTaskId(@Param("taskId") Long taskId);


    /*  извеждане на отчети по дадена задача с брой отработени часове в даден интервал;


    извеждане на отчета по дадена задача с най-голям брой отработени часове;
    изчисляване на отработените часове за изпълнение на задача по подаден номер.*/
}
