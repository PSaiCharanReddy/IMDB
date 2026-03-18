package com.movie.imdb.repositories;
import com.movie.imdb.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByName(String name);
}
