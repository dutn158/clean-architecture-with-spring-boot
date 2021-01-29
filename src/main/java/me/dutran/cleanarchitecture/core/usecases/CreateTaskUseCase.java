package me.dutran.cleanarchitecture.core.usecases;

import lombok.Value;
import me.dutran.cleanarchitecture.core.UseCase;
import me.dutran.cleanarchitecture.core.domain.DomainException;
import me.dutran.cleanarchitecture.core.domain.Task;


public class CreateTaskUseCase extends UseCase<CreateTaskUseCase.InputValues, CreateTaskUseCase.OutputValues> {

    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Task task = new Task(input.title, input.description);
        if (!task.isTitleValid()) {
            throw new DomainException("Title must have at least 6 characters");
        }
        return new OutputValues(taskRepository.save(task));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String title;
        String description;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        Task task;
    }
}
