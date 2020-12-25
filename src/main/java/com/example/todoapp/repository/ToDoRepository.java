package com.example.todoapp.repository;


import com.example.todoapp.models.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByTitleContaining(String title);
    List<ToDo> findAllByUserId(Long id);
    List<ToDo> findByDateBetween(Date startDate, Date endDate);
    List<ToDo> findByUserIdAndDateBetween(Long id, Date startDate, Date endDate);
    Optional<ToDo> findById(Long id);


}
