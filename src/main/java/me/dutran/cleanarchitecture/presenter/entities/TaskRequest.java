package me.dutran.cleanarchitecture.presenter.entities;

import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String description;
}
