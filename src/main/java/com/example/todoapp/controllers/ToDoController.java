package com.example.todoapp.controllers;

import com.example.todoapp.request.ToDoRequest;
import com.example.todoapp.response.ToDoListResponse;
import com.example.todoapp.response.ToDoResponse;
import com.example.todoapp.service.ToDoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ToDoController {

    @Autowired
    private final ToDoServiceImpl toDoService;


    @CrossOrigin
    @GetMapping("/todo/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ToDoResponse> getToDo(@PathVariable(name = "id") long id, Principal principal) {
        System.out.println(toDoService.toDoSecurity(id,principal));
        try {
            if(toDoService.toDoSecurity(id,principal)){
                ToDoResponse res = new ToDoResponse();
                res.setToDoDto(toDoService.getToDo(id));
                return ResponseEntity.ok(res);
            }
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @GetMapping("/todos/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ToDoListResponse> getToDoListByUserId(@PathVariable(name = "userId") long userId, Principal principal) {
        try {
            if(toDoService.userSecurity(userId,principal)){
                ToDoListResponse res = new ToDoListResponse();
                res.setToDoList(toDoService.getListByUserId(userId));
                return ResponseEntity.ok(res);
            }
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/todo/{userId}/{startDate}/{endDate}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ToDoListResponse> getToDoListByDate(@PathVariable(name = "userId") long userId,
                                                           @PathVariable(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
                                                           @PathVariable(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                           Principal principal) {
        try {
            if(toDoService.userSecurity(userId,principal)){
                ToDoListResponse res = new ToDoListResponse();
                res.setToDoList(toDoService.getListByDate(userId,startDate,endDate));
                return ResponseEntity.ok(res);
            }
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @CrossOrigin
    @PostMapping("/todo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ToDoResponse> addToDo(@RequestBody ToDoRequest req) {
        try {
            ToDoResponse res = new ToDoResponse();
            res.setToDoDto(toDoService.addToDo(req.getToDoDto()));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @DeleteMapping("todo/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity deleteToDo(@PathVariable(name = "id") long id,Principal principal) {
        System.out.println(id);
        try {
            if(toDoService.toDoSecurity(id,principal)) {
                toDoService.deleteToDo(id);
                return ResponseEntity.ok(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin
    @PutMapping("todo")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ToDoResponse> updateToDo(@RequestBody ToDoRequest req){
        try{
            ToDoResponse res = new ToDoResponse();
            res.setToDoDto(toDoService.updateToDo(req.getToDoDto()));
            return ResponseEntity.ok(res);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}

//    @CrossOrigin
//    @GetMapping("search/{title}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<ToDoListResponse> getToDoListByTitle(@PathVariable(name = "title") String title ) {
//        try {
//            ToDoListResponse res = new ToDoListResponse();
//            res.setToDoList(toDoService.getListByContainingTitle(title));
//            return ResponseEntity.ok(res);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity(HttpStatus.NO_CONTENT);
//        }
//    }



















































//public class ToDoController {
//
//    @Autowired
//    ToDoListService toDoListService;
//
//    @GetMapping("/todos")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<List<ToDo>> getAllToDoLists(@RequestParam Long id) {
//        try {
//            if (toDoListService.getAllUserToDoList(id).isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(toDoListService.getAllUserToDoList(id), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//    @GetMapping("/todos/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<ToDo> getToDoListById(@PathVariable("id") Long id) {
//
//        if (toDoListService.getToDoById(id).isPresent()) {
//            return new ResponseEntity<>(toDoListService.getToDoById(id).get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//
//    @PostMapping("/todos")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<ToDo> createToDoList(@RequestBody ToDo toDo) {
//        try {
//            ToDo _toDo = toDoListService.createToDo(toDo);
//            return new ResponseEntity<>(_toDo, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/todos/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<ToDo> updateToDoList(@PathVariable("id") Long id, @RequestBody ToDo toDo) {
//        try { ;
//            return new ResponseEntity<>(toDoListService.updateToDo(id,toDo), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/todos/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<HttpStatus> deleteToDoList(@PathVariable("id") Long id) {
//        if (toDoListService.deleteToDo(id)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/todos")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<HttpStatus> deleteAllToDoLists() {
//        if (toDoListService.deleteAllToDo()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
