package me.dutran.cleanarchitecture.db.repositories;

import me.dutran.cleanarchitecture.core.domain.Task;
import me.dutran.cleanarchitecture.core.usecases.TaskRepository;
import me.dutran.cleanarchitecture.db.entities.TaskData;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final JpaTaskRepository jpaTaskRepository;

    public TaskRepositoryImpl(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }

    @Override
    public Task save(Task task) {
        TaskData taskData = TaskData.from(task);
        return jpaTaskRepository.save(taskData).fromThis();
    }

    @Override
    public List<Task> list() {
        List<TaskData> list = jpaTaskRepository.findAll();

        return list.stream()
                .map(TaskData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Task> getById(Long id) {
        Optional<TaskData> taskData = jpaTaskRepository.findById(id);
        return taskData.map(TaskData::fromThis);
    }

    @Override
    public boolean delete(Task task) {
        TaskData taskData = TaskData.from(task);
        jpaTaskRepository.delete(taskData);
        return true;
    }
}
