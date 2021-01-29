package me.dutran.cleanarchitecture.core.usecases;

import lombok.Value;
import me.dutran.cleanarchitecture.core.UseCase;
import me.dutran.cleanarchitecture.core.domain.NotFoundException;
import me.dutran.cleanarchitecture.core.domain.Task;

import java.util.Optional;

public class DeleteTaskUseCase extends UseCase<DeleteTaskUseCase.InputValues, DeleteTaskUseCase.OutputValues> {

    private final TaskRepository taskRepository;

    public DeleteTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Optional<Task> task = taskRepository.getById(input.id);
        if (!task.isPresent()) {
            throw new NotFoundException("Task %d not found", input.id);
        }

        return new OutputValues(taskRepository.delete(task.get()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        Long id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        boolean success;
    }
}
