package com.movie.imdb.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.imdb.dto.MovieDto;
import com.movie.imdb.model.Actor;
import com.movie.imdb.model.Cast;
import com.movie.imdb.model.CastType;
import com.movie.imdb.model.Director;
import com.movie.imdb.model.Genre;
import com.movie.imdb.model.Movie;
import com.movie.imdb.model.Writer;
import com.movie.imdb.repositories.ActorRepository;
import com.movie.imdb.repositories.CastRepository;
import com.movie.imdb.repositories.DirectorRepository;
import com.movie.imdb.repositories.MovieRepository;
import com.movie.imdb.repositories.WriterRepository;
import com.movie.imdb.services.MovieService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final WriterRepository writerRepository;
    private final ActorRepository actorRepository;
    private final CastRepository castRepository;

    @Override
    @Transactional
    public void addMovie(MovieDto dto) {
        Director director = resolveDirector(dto.getDirectorName());
        Writer writer = resolveWriter(dto.getWriterName());
        Genre genre = parseGenre(dto.getGenre());

        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDirector(director);
        movie.setWriter(writer);
        movie.setGenreType(genre);
        movie.setDescription(dto.getDescription());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setRating(dto.getRating());

        Movie savedMovie = movieRepository.save(movie);

        if (dto.getCast() != null) {
            for (MovieDto.CastDto castDto : dto.getCast()) {
                Actor actor = actorRepository.findByName(castDto.getActorName());
                if (actor == null) {
                    actor = actorRepository.save(new Actor(null, castDto.getActorName(), null));
                }
                Cast cast = new Cast();
                cast.setMovie(savedMovie);
                cast.setActor(actor);
                cast.setCastType(CastType.valueOf(castDto.getCastType().toUpperCase()));
                castRepository.save(cast);
            }
        }
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return toDto(movie);
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean updateMovie(Long id, MovieDto dto) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) return false;

        if (dto.getTitle() != null) movie.setTitle(dto.getTitle());
        if (dto.getDescription() != null) movie.setDescription(dto.getDescription());
        if (dto.getReleaseYear() > 0) movie.setReleaseYear(dto.getReleaseYear());
        if (dto.getRating() > 0) movie.setRating(dto.getRating());
        if (dto.getGenre() != null && !dto.getGenre().isBlank()) {
            movie.setGenreType(Genre.valueOf(dto.getGenre().toUpperCase()));
        }
        if (dto.getDirectorName() != null && !dto.getDirectorName().isBlank()) {
            movie.setDirector(resolveDirector(dto.getDirectorName()));
        }
        if (dto.getWriterName() != null && !dto.getWriterName().isBlank()) {
            movie.setWriter(resolveWriter(dto.getWriterName()));
        }

        movieRepository.save(movie);
        return true;
    }

    @Override
    public boolean deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) return false;
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MovieDto> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> searchByDirector(String directorName) {
        return movieRepository.findByDirector_NameContainingIgnoreCase(directorName).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> searchByActor(String actorName) {
        return movieRepository.findDistinctByCasts_Actor_NameContainingIgnoreCase(actorName).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> searchByGenre(String genre) {
        Genre genreEnum = Genre.valueOf(genre.toUpperCase());
        return movieRepository.findByGenreType(genreEnum).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> searchByYear(int year) {
        return movieRepository.findByReleaseYear(year).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    // ---- Helpers ----

    private Director resolveDirector(String name) {
        if (name == null || name.isBlank()) return null;
        Director d = directorRepository.findByName(name);
        return d != null ? d : directorRepository.save(new Director(null, name));
    }

    private Writer resolveWriter(String name) {
        if (name == null || name.isBlank()) return null;
        Writer w = writerRepository.findByName(name);
        return w != null ? w : writerRepository.save(new Writer(null, name));
    }

    private Genre parseGenre(String genre) {
        if (genre == null || genre.isBlank()) return null;
        return Genre.valueOf(genre.toUpperCase());
    }

    public MovieDto toDto(Movie movie) {
        List<MovieDto.CastDto> castDtos = movie.getCasts() == null ? List.of() :
                movie.getCasts().stream()
                        .map(c -> MovieDto.CastDto.builder()
                                .actorName(c.getActor() != null ? c.getActor().getName() : null)
                                .castType(c.getCastType() != null ? c.getCastType().name() : null)
                                .build())
                        .collect(Collectors.toList());

        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenreType() != null ? movie.getGenreType().name() : null)
                .directorName(movie.getDirector() != null ? movie.getDirector().getName() : null)
                .writerName(movie.getWriter() != null ? movie.getWriter().getName() : null)
                .cast(castDtos)
                .description(movie.getDescription())
                .releaseYear(movie.getReleaseYear())
                .rating(movie.getRating())
                .build();
    }
}
