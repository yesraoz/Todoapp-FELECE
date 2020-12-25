package com.example.todoapp.request;

import com.example.todoapp.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserRequest {
    @JsonProperty("UserDto")
    UserDto userDto;
}