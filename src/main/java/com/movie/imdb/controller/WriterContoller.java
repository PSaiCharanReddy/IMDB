package com.movie.imdb.controller;
import com.movie.imdb.model.Writer;
import com.movie.imdb.services.WriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;   
@RestController
@RequestMapping("/writers")
@RequiredArgsConstructor
public class WriterContoller {
    private final WriterService writerService;

    @PostMapping
    public ResponseEntity<Writer> createWriter(@RequestBody Writer writer) {
        Writer createdWriter = writerService.createWriter(writer);
        return new ResponseEntity<>(createdWriter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Writer> getWriterById(@PathVariable Long id) {
        Writer writer = writerService.getWriterById(id);
        if (writer != null) {
            return new ResponseEntity<>(writer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Writer> updateWriter(@PathVariable Long id, @RequestBody Writer writer) {
        Writer updatedWriter = writerService.updateWriter(id, writer);
        if (updatedWriter != null) {
            return new ResponseEntity<>(updatedWriter, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWriter(@PathVariable Long id) {
        boolean deleted = writerService.deleteWriter(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
