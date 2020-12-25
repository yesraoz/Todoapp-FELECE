package com.example.todoapp.models;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

}