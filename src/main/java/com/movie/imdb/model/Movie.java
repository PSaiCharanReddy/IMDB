package com.movie.imdb.model;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "movies")
@Data
public class Movie {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
}
