package com.movie.imdb.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.movie.imdb.services.*;

import lombok.RequiredArgsConstructor;
import com.movie.imdb.model.*;
import com.movie.imdb.repositories.ActorRepository;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl  implements ActorService {
    private final ActorRepository actorRepository;
    @Override
    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }
    @Override
    public Actor getActorById(Long id) {
        return actorRepository.findById(id).orElseThrow(() -> new RuntimeException("Actor not found"));
    }
    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }
    @Override
    public Actor updateActor(Long id, Actor actor) {
        Actor existingActor = getActorById(id);
        existingActor.setName(actor.getName());
        existingActor.setGender(actor.getGender());
        return actorRepository.save(existingActor);
    }
    @Override   
    public void deleteActor(Long id) {
        Actor existingActor = getActorById(id);
        actorRepository.delete(existingActor);
    }


}
