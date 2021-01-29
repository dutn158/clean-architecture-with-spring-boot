package me.dutran.cleanarchitecture.core.usecases;

import lombok.Value;
import me.dutran.cleanarchitecture.core.UseCase;
import me.dutran.cleanarchitecture.core.domain.Task;

import java.util.List;

public class GetTasksUseCase extends UseCase<GetTasksUseCase.InputValues, GetTasksUseCase.OutputValues> {

    private final TaskRepository taskRepository;

    public GetTasksUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(taskRepository.list());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<Task> tasks;
    }
}
