package com.movie.imdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "casts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Enumerated(EnumType.STRING)
    private CastType castType;
}
