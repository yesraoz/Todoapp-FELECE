package com.example.todoapp.controllers;


import com.example.todoapp.request.UserRequest;
import com.example.todoapp.response.ToDoListResponse;
import com.example.todoapp.response.UserListResponse;
import com.example.todoapp.response.UserResponse;
import com.example.todoapp.security.payload.request.SignupRequest;
import com.example.todoapp.security.services.AuthService;
import com.example.todoapp.service.ToDoService;
import com.example.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private final UserService userService;
    @Autowired
    private final ToDoService toDoService;
    @Autowired
    AuthService authService;

    @CrossOrigin
    @GetMapping("/todolist")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ToDoListResponse> getToDoList(){
        try{
            ToDoListResponse res = new ToDoListResponse();
            res.setToDoList(toDoService.getList());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ToDoListResponse> getToDoListByUserId(@PathVariable(name = "userId") long userId) {
        try {
            ToDoListResponse res = new ToDoListResponse();
            res.setToDoList(toDoService.getListByUserId(userId));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @GetMapping("/todos/{startDate}/{endDate}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ToDoListResponse> getToDoListByDate(@PathVariable(name = "startDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                           @PathVariable(name = "endDate")   @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        System.out.println(startDate + " "+ endDate);
        try {
            ToDoListResponse res = new ToDoListResponse();
            res.setToDoList(userService.getListByDateBetween(startDate,endDate));
            return ResponseEntity.ok(res);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUser(@PathVariable(name = "id") long id) {
        try {
            UserResponse res = new UserResponse();
            res.setUserDto(userService.getUser(id));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserListResponse> getUsers() {
        try {
            UserListResponse res = new UserListResponse();
            res.setUserDtoList(userService.getUsers());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody SignupRequest signupRequest) {
        try {
            return ResponseEntity.ok(authService.signUp(signupRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest req)  {
        try {
            UserResponse res = new UserResponse();
            res.setUserDto(userService.updateUser(req.getUserDto()));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteUser(@PathVariable(name = "id") long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
