package com.movie.imdb.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movie.imdb.model.Writer;
import com.movie.imdb.repositories.WriterRepository;
import com.movie.imdb.services.WriterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WriterServiceImpl implements WriterService {

    private final WriterRepository writerRepository;

    @Override
    public Writer createWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public Writer getWriterById(Long id) {
        return writerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Writer not found with id: " + id));
    }

    @Override
    public List<Writer> getAllWriters() {
        return writerRepository.findAll();
    }

    @Override
    public Writer updateWriter(Long id, Writer writer) {
        Writer existing = getWriterById(id);
        existing.setName(writer.getName());
        return writerRepository.save(existing);
    }

    @Override
    public boolean deleteWriter(Long id) {
        if (!writerRepository.existsById(id)) {
            return false;
        }
        writerRepository.deleteById(id);
        return true;
    }
}
