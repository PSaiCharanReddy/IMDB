package com.movie.imdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "writers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Writer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
