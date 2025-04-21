package bg.tu_varna.sit.taskmanager16042025.model.entity;

import bg.tu_varna.sit.taskmanager16042025.model.dto.response.ReportResponseDto;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String summary;
    @Column(name="description", length=50, nullable=false, unique=false)
    private String description;
    private LocalDateTime deadline;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Report> reports;
}

/*Task:

    уникален идентификатор на задачата;
    резюме;
    описание;
    краен срок за изпълнение;
    колекция с отчети към задачата.

Report:

    уникален идентификатор на отчета;
    съдържание;
    заработени часове;
    дата и час на създаване на отчета;
    дата и час на последна актуализация на отчета;
    задачата, към която принадлежи отчетът.
*/