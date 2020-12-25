package com.example.todoapp.response;

import com.example.todoapp.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserListResponse {
    private List<UserDto> userDtoList;
}