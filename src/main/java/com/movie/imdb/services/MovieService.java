package com.movie.imdb.services;

import java.util.List;

import com.movie.imdb.dto.MovieDto;

public interface MovieService {

    void addMovie(MovieDto movieDto);

    MovieDto getMovieById(Long id);

    List<MovieDto> getAllMovies();

    boolean updateMovie(Long id, MovieDto movieDto);

    boolean deleteMovie(Long id);

    List<MovieDto> searchByTitle(String title);

    List<MovieDto> searchByDirector(String directorName);

    List<MovieDto> searchByActor(String actorName);

    List<MovieDto> searchByGenre(String genre);

    List<MovieDto> searchByYear(int year);
}
