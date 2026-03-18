package com.movie.imdb.services;

import java.util.List;

import com.movie.imdb.model.Actor;

public interface ActorService {

    Actor addActor(Actor actor);

    Actor getActorById(Long id);

    List<Actor> getAllActors();

    Actor updateActor(Long id, Actor actor);

    void deleteActor(Long id);

    
}
