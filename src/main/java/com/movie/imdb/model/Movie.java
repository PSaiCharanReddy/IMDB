package com.movie.imdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "movies")
@Data
public class Movie {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String title;

    
    @JoinColumn(name = "director_id")
    @ManyToOne
    private Director director;

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private Writer writer;
    @OneToMany(mappedBy = "movie")
    private List<Cast> casts;

    @Enumerated(EnumType.STRING)
    private Genre genreType;

    private String desc;

    private int releaseYear;
    private double rating;

}
