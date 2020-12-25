package com.example.todoapp.mapper;


import com.example.todoapp.dto.ToDoDto;
import com.example.todoapp.models.ToDo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToDoMapper {
    @Named("toToDo")
    ToDo toToDo(ToDoDto toDoDto);

    @Named("toToDoDto")
    ToDoDto toToDoDto(ToDo toDo);

    @IterableMapping(qualifiedByName = "toToDoList")
    List<ToDo> toToDoList(List<ToDoDto> toDoDtoList);

    @IterableMapping(qualifiedByName = "toToDoDtoList")
    List<ToDoDto> toToDoDtoList(List<ToDo> toDos);
}

