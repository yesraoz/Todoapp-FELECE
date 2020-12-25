package com.example.todoapp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ToDo extends BaseEntity implements Serializable {

    @Column(nullable = true)
    private String title;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private Date date;
    @Column(nullable = true)
    private String status;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;


}
