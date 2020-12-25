package com.example.todoapp.service;


import com.example.todoapp.dto.ToDoDto;
import com.example.todoapp.dto.UserDto;
import com.example.todoapp.mapper.ToDoMapper;
import com.example.todoapp.mapper.UserMapper;
import com.example.todoapp.models.User;
import com.example.todoapp.repository.ToDoRepository;
import com.example.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ToDoMapper toDoMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        return userMapper.toUserDto(userRepository.save(userMapper.toUser(userDto)));
    }

    @Override
    public UserDto getUser(long userId) {
        return userMapper
                .toUserDto(userRepository
                        .findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User : " + userId + "does not exist !")));
    }
    @Override
    public UserDto getByUserName(String username) {
        return userMapper.toUserDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User userDb = userRepository.findById(userDto.getId())
                    .orElseThrow(() -> new IllegalArgumentException("User : " + userDto.getId() + "does not exist !"));
        System.out.println(userDb);
            userDb.setEmail(userDto.getEmail());
            userDb.setUsername(userDto.getUsername());
            userDb.setName(userDto.getName());
            userDb.setLastName(userDto.getLastName());
        System.out.println(userMapper.toUserDto(userRepository.save(userDb)));
        return userMapper.toUserDto(userRepository.save(userDb));
    }

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User : " + id + "does not exist !")));
    }

    @Override
    public List<ToDoDto> getListByDateBetween(Date startDate, Date endDate) {
        return toDoMapper.toToDoDtoList(toDoRepository.findByDateBetween(startDate,endDate));
    }
}
