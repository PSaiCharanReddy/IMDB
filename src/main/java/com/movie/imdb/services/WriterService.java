package com.movie.imdb.services;

import java.util.List;

import com.movie.imdb.model.Writer;

public interface WriterService {

    Writer createWriter(Writer writer);

    Writer getWriterById(Long id);

    List<Writer> getAllWriters();

    Writer updateWriter(Long id, Writer writer);

    boolean deleteWriter(Long id);
}
