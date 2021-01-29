package me.dutran.cleanarchitecture.presenter.entities;

import lombok.Value;

@Value
public class ApiResponse {
    boolean success;
    String message;
}
