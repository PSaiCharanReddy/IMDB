package com.movie.imdb.services;

import java.util.List;

import com.movie.imdb.model.Director;

public interface DirectorService {

    Director createDirector(Director director);

    Director getDirectorById(Long id);

    List<Director> getAllDirectors();

    Director updateDirector(Long id, Director director);

    boolean deleteDirector(Long id);
}
