package com.movie.imdb.controller;
import com.movie.imdb.model.Movie;
import com.movie.imdb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.movie.imdb.dto.MovieDto;
@RestController
@RequestMapping("/Movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieDto movie) {
        movieService.addMovie(movie);
        return ResponseEntity.ok("Movie added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);   
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody MovieDto movie) {
        boolean updated = movieService.updateMovie(id, movie);
        if (updated) {
            return ResponseEntity.ok("Movie updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            return ResponseEntity.ok("Movie deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}