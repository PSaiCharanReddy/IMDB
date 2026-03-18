package com.movie.imdb.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movie.imdb.model.Director;
import com.movie.imdb.repositories.DirectorRepository;
import com.movie.imdb.services.DirectorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    public Director createDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public Director getDirectorById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Director not found with id: " + id));
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director updateDirector(Long id, Director director) {
        Director existing = getDirectorById(id);
        existing.setName(director.getName());
        return directorRepository.save(existing);
    }

    @Override
    public boolean deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) {
            return false;
        }
        directorRepository.deleteById(id);
        return true;
    }
}
