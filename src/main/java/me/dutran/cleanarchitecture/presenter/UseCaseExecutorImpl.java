package me.dutran.cleanarchitecture.presenter;

import me.dutran.cleanarchitecture.core.UseCase;
import me.dutran.cleanarchitecture.core.UseCaseExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<RX> execute(UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
        return CompletableFuture.supplyAsync(() -> input)
                .thenApply(useCase::execute)
                .thenApply(outputMapper);
    }
}
