package com.movie.imdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "casts")
@Data
public class Cast {


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Enumerated(EnumType.STRING)
    private CastType castType;
}