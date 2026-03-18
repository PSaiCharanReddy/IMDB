package com.movie.imdb.dto;
import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class MovieDto {
    private String title;
    private String genre;
    private String directorName;
    private String writerName;
    private List<String> castNames;
    private int releaseYear;
    private double rating;

}