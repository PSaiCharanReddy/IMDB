package com.movie.imdb.repositories;
import com.movie.imdb.model.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Long> {
    Writer findByName(String name);
}
