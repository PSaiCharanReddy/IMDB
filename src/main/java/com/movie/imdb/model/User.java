package com.movie.imdb.model;
import org.springframework.core.annotation.MergedAnnotation;

import jakarta.persistence.*;
import lombok.*;    
import com.movie.imdb.model.*;



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