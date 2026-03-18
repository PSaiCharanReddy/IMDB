package com.movie.imdb.repositories;
import com.movie.imdb.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MovieRepository extends JpaRepository<Director, Long> {    
    
}