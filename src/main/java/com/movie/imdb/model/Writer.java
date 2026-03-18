package com.movie.imdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "writers")
@Data
class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}