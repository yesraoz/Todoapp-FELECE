package com.example.todoapp.response;

import com.example.todoapp.dto.ToDoDto;
import lombok.Data;

import java.util.List;

@Data
public class ToDoListResponse {
    List<ToDoDto> toDoList;
}
