package com.movie.imdb.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private Long id;
    private String title;
    private String genre;
    private String directorName;
    private String writerName;
    private List<CastDto> cast;
    private String description;
    private int releaseYear;
    private double rating;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CastDto {
        private String actorName;
        private String castType;
    }
}
