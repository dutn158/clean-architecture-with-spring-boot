package me.dutran.cleanarchitecture.db.repositories;

import me.dutran.cleanarchitecture.db.entities.TaskData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<TaskData, Long> {
}
