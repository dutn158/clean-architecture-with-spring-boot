package me.dutran.cleanarchitecture.core.usecases;

import me.dutran.cleanarchitecture.core.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    List<Task> list();

    Optional<Task> getById(Long id);

    boolean delete(Task task);
}
