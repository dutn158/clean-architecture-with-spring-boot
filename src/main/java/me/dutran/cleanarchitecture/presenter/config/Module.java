package me.dutran.cleanarchitecture.presenter.config;

import me.dutran.cleanarchitecture.core.usecases.CreateTaskUseCase;
import me.dutran.cleanarchitecture.core.usecases.DeleteTaskUseCase;
import me.dutran.cleanarchitecture.core.usecases.GetTasksUseCase;
import me.dutran.cleanarchitecture.core.usecases.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepository taskRepository) {
        return new CreateTaskUseCase(taskRepository);
    }

    @Bean
    public GetTasksUseCase getTasksUseCase(TaskRepository taskRepository) {
        return new GetTasksUseCase(taskRepository);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskRepository taskRepository) {
        return new DeleteTaskUseCase(taskRepository);
    }
}
