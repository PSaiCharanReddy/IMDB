package com.movie.imdb.controller;

import com.movie.imdb.dto.MovieDto;
import com.movie.imdb.services.MovieService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movieDto) {
        movieService.addMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.getMovieById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        boolean updated = movieService.updateMovie(id, movieDto);
        return updated
                ? ResponseEntity.ok("Movie updated successfully")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteMovie(id);
        return deleted
                ? ResponseEntity.ok("Movie deleted successfully")
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<MovieDto>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchByTitle(title));
    }

    @GetMapping("/search/director")
    public ResponseEntity<List<MovieDto>> searchByDirector(@RequestParam String name) {
        return ResponseEntity.ok(movieService.searchByDirector(name));
    }

    @GetMapping("/search/actor")
    public ResponseEntity<List<MovieDto>> searchByActor(@RequestParam String name) {
        return ResponseEntity.ok(movieService.searchByActor(name));
    }

    @GetMapping("/search/genre")
    public ResponseEntity<List<MovieDto>> searchByGenre(@RequestParam String genre) {
        try {
            return ResponseEntity.ok(movieService.searchByGenre(genre));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search/year")
    public ResponseEntity<List<MovieDto>> searchByYear(@RequestParam int year) {
        return ResponseEntity.ok(movieService.searchByYear(year));
    }
}
