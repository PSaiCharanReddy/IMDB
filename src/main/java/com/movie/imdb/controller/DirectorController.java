package com.movie.imdb.controller;
import com.movie.imdb.model.Director;
import com.movie.imdb.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor

public class DirectorController {
    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        Director createdDirector = directorService.createDirector(director);
        return new ResponseEntity<>(createdDirector, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Long id) {
        Director director = directorService.getDirectorById(id);
        if (director != null) {
            return new ResponseEntity<>(director, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> updateDirector(@PathVariable Long id, @RequestBody Director director) {
        Director updatedDirector = directorService.updateDirector(id, director);
        if (updatedDirector != null) {
            return new ResponseEntity<>(updatedDirector, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        boolean deleted = directorService.deleteDirector(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
