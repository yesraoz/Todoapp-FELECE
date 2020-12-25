package com.example.todoapp.dto;

import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDto extends BaseDto {
    private String title;
    private String description;
    private Date date;
    private String status;
    private UserDto userDto;
}
