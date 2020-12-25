package com.example.todoapp.service;


import com.example.todoapp.dto.ToDoDto;
import com.example.todoapp.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);
    void deleteUser(long id);
    UserDto updateUser(UserDto userDto);
    UserDto getUser(long userId);
    UserDto getByUserName(String username);
    List<UserDto> getUsers();
    List<ToDoDto> getListByDateBetween(Date startDate, Date endDate);

}