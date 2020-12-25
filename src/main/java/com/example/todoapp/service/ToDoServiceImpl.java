package com.example.todoapp.service;


import com.example.todoapp.dto.ToDoDto;
import com.example.todoapp.mapper.ToDoMapper;
import com.example.todoapp.models.ToDo;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.ToDoRepository;
import com.example.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ToDoMapper toDoMapper;


    @Override
    public ToDoDto addToDo(ToDoDto recipeDto) {
        ToDo toDo = toDoMapper.toToDo(recipeDto);
        try {
            toDo.setUser(userRepository.getOne(recipeDto.getUserDto().getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toDoMapper.toToDoDto(toDoRepository.save(toDo));
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto){
        ToDo todoDb = toDoRepository.findById(toDoDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("ToDo : " + toDoDto.getId() + "does not exist !"));
            todoDb.setTitle(toDoDto.getTitle());
            todoDb.setDescription(toDoDto.getDescription());
            todoDb.setDate(toDoDto.getDate());
            todoDb.setStatus(todoDb.getStatus());
        return toDoMapper.toToDoDto(toDoRepository.save(todoDb));
    }


    @Override
    public List<ToDoDto> getList() {
        return toDoMapper.toToDoDtoList(toDoRepository.findAll());
    }

    @Override
    public List<ToDoDto> getListByUserId(long userId) {
        return toDoMapper.toToDoDtoList(toDoRepository.findAllByUserId(userId));
    }

    @Override
    public List<ToDoDto> getListByDate(Long id,Date startDate, Date endDate) {
        return toDoMapper.toToDoDtoList(toDoRepository.findByUserIdAndDateBetween(id, startDate,endDate));
    }

    @Override
    public ToDoDto getToDo(long id) {
        ToDoDto recipeDto = toDoMapper.toToDoDto(toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo : " + id + "does not exist !")));
        return recipeDto;
    }

    @Override
    public void deleteToDo(long id) {
        toDoRepository
                .delete(toDoRepository
                        .findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("ToDo : " + id + "does not exist !")));
    }

    @Override
    public List<ToDoDto> getListByContainingTitle(String title) {
        return toDoMapper.toToDoDtoList(toDoRepository.findByTitleContaining(title));
    }

    public boolean toDoSecurity(Long id, Principal principal){
        Optional<ToDo> todo = toDoRepository.findById(id);
        String todoUsername = todo.get().getUser().getUsername();
        System.out.println(todoUsername);
        System.out.println(principal.getName());
        if (todoUsername.equals(principal.getName())){
            return true;
        }
        return false;
    }

    public boolean userSecurity(Long id, Principal principal){
        Optional<User> user = userRepository.findById(id);
        String Username = user.get().getUsername();
        System.out.println(Username);
        System.out.println(principal.getName());
        if (Username.equals(principal.getName())){
            return true;
        }
        return false;
    }
}
