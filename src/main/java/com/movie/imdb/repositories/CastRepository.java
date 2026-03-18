package com.movie.imdb.repositories;
import com.movie.imdb.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CastRepository extends JpaRepository<Cast, Long> {

}
