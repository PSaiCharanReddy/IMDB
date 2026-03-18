package com.movie.imdb.repositories;
import com.movie.imdb.model.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface MovieRepository extends JpaRepository<Movie, Long> {
  public List<Movie> findByTitleContaining(String title);

    public List<Movie>  findByReleaseYear(int releaseYear);
    public  List<Movie> findByRating(double rating);
    public  List<Movie> findByGenreTypeContaining(String genreType);
    public  List<Movie> findByDirectorNameContaining(String directorName);
    public  List<Movie> findByWriterNameContaining(String writerName);

   public List<Movie> findDistinctByCasts_Actor_NameContaining(String actorName);
}
