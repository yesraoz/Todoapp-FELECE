package com.example.todoapp.request;

import com.example.todoapp.dto.ToDoDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ToDoRequest {
    @JsonProperty("ToDoDto")
    ToDoDto toDoDto;
}
