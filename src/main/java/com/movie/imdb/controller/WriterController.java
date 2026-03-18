package com.movie.imdb.controller;

import com.movie.imdb.model.Writer;
import com.movie.imdb.services.WriterService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/writers")
@RequiredArgsConstructor
public class WriterController {

    private final WriterService writerService;

    @PostMapping("/add")
    public ResponseEntity<Writer> createWriter(@RequestBody Writer writer) {
        return new ResponseEntity<>(writerService.createWriter(writer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Writer> getWriterById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(writerService.getWriterById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Writer>> getAllWriters() {
        return ResponseEntity.ok(writerService.getAllWriters());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Writer> updateWriter(@PathVariable Long id, @RequestBody Writer writer) {
        try {
            return ResponseEntity.ok(writerService.updateWriter(id, writer));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteWriter(@PathVariable Long id) {
        boolean deleted = writerService.deleteWriter(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
