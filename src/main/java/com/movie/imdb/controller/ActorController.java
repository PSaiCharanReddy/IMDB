package com.movie.imdb.controller;
import com.movie.imdb.model.Actor;
import com.movie.imdb.services.ActorService;
import lombok.RequiredArgsConstructor;  
@RESTController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;
    @POSTMapping("/add")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor) {
        Actor createdActor = actorService.addActor(actor);
        return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        Actor actor = actorService.getActorById(id);
        return new ResponseEntity<>(actor, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Actor actor) {
        Actor updatedActor = actorService.updateActor(id, actor);
        return new ResponseEntity<>(updatedActor, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}