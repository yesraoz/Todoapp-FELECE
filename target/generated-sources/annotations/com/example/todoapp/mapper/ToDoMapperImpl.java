package com.example.todoapp.mapper;

import com.example.todoapp.dto.ToDoDto;
import com.example.todoapp.models.ToDo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T23:43:35+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.5 (Oracle Corporation)"
)
@Component
public class ToDoMapperImpl implements ToDoMapper {

    @Override
    public ToDo toToDo(ToDoDto toDoDto) {
        if ( toDoDto == null ) {
            return null;
        }

        ToDo toDo = new ToDo();

        toDo.setId( toDoDto.getId() );
        toDo.setTitle( toDoDto.getTitle() );
        toDo.setDescription( toDoDto.getDescription() );
        toDo.setDate( toDoDto.getDate() );
        toDo.setStatus( toDoDto.getStatus() );

        return toDo;
    }

    @Override
    public ToDoDto toToDoDto(ToDo toDo) {
        if ( toDo == null ) {
            return null;
        }

        ToDoDto toDoDto = new ToDoDto();

        toDoDto.setId( toDo.getId() );
        toDoDto.setTitle( toDo.getTitle() );
        toDoDto.setDescription( toDo.getDescription() );
        toDoDto.setDate( toDo.getDate() );
        toDoDto.setStatus( toDo.getStatus() );

        return toDoDto;
    }

    @Override
    public List<ToDo> toToDoList(List<ToDoDto> toDoDtoList) {
        if ( toDoDtoList == null ) {
            return null;
        }

        List<ToDo> list = new ArrayList<ToDo>( toDoDtoList.size() );
        for ( ToDoDto toDoDto : toDoDtoList ) {
            list.add( toToDo( toDoDto ) );
        }

        return list;
    }

    @Override
    public List<ToDoDto> toToDoDtoList(List<ToDo> toDos) {
        if ( toDos == null ) {
            return null;
        }

        List<ToDoDto> list = new ArrayList<ToDoDto>( toDos.size() );
        for ( ToDo toDo : toDos ) {
            list.add( toToDoDto( toDo ) );
        }

        return list;
    }
}
