package com.movie.imdb.repositories;
import com.movie.imdb.model.Movie;
import com.movie.imdb.model.Genre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByReleaseYear(int releaseYear);

    List<Movie> findByRating(double rating);

    List<Movie> findByGenreType(Genre genreType);

    List<Movie> findByDirector_NameContainingIgnoreCase(String directorName);

    List<Movie> findByWriter_NameContainingIgnoreCase(String writerName);

    List<Movie> findDistinctByCasts_Actor_NameContainingIgnoreCase(String actorName);
}
