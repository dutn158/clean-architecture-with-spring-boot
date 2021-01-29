package me.dutran.cleanarchitecture.db.entities;

import lombok.Data;
import me.dutran.cleanarchitecture.core.domain.Task;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class TaskData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public TaskData() {
    }

    public TaskData(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static TaskData from(Task task) {
        return new TaskData(
                task.getTitle(),
                task.getDescription()
        );
    }

    public Task fromThis() {
        return new Task(
                this.getId(),
                this.getTitle(),
                this.getDescription()
        );
    }
}
