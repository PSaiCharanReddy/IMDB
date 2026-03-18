package com.movie.imdb.model;

import jakarta.persistence.*;
import lombok.*;    



@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
   
    @Enumerated(EnumType.STRING)
    private Role role;
}