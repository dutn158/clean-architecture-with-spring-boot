package me.dutran.cleanarchitecture.presenter.controllers;

import me.dutran.cleanarchitecture.core.UseCaseExecutor;
import me.dutran.cleanarchitecture.core.usecases.CreateTaskUseCase;
import me.dutran.cleanarchitecture.core.usecases.DeleteTaskUseCase;
import me.dutran.cleanarchitecture.core.usecases.GetTasksUseCase;
import me.dutran.cleanarchitecture.presenter.entities.TaskRequest;
import me.dutran.cleanarchitecture.presenter.entities.TaskResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private final UseCaseExecutor useCaseExecutor;
    private final CreateTaskUseCase createTaskUseCase;
    private final GetTasksUseCase getTasksUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskController(UseCaseExecutor useCaseExecutor, CreateTaskUseCase createTaskUseCase, GetTasksUseCase getTasksUseCase, DeleteTaskUseCase deleteTaskUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createTaskUseCase = createTaskUseCase;
        this.getTasksUseCase = getTasksUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }

    @PostMapping("/tasks")
    @ResponseBody
    public CompletableFuture<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return useCaseExecutor.execute(
                createTaskUseCase,
                new CreateTaskUseCase.InputValues(request.getTitle(), request.getDescription()),
                (outputValues) -> TaskResponse.from(outputValues.getTask())
        );
    }

    @GetMapping("/tasks")
    public CompletableFuture<List<TaskResponse>> getTasks() {
        return useCaseExecutor.execute(
                getTasksUseCase,
                new GetTasksUseCase.InputValues(),
                (outputValues) -> outputValues.getTasks().stream().map(TaskResponse::from).collect(Collectors.toList())
        );
    }

    @DeleteMapping("/tasks/{id}")
    public CompletableFuture<Boolean> deleteTask(@PathVariable("id") Long id) {
        return useCaseExecutor.execute(
                deleteTaskUseCase,
                new DeleteTaskUseCase.InputValues(id),
                DeleteTaskUseCase.OutputValues::isSuccess
        );
    }
}
