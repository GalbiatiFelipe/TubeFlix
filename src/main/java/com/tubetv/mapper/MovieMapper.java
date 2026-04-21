package com.tubetv.mapper;

import com.tubetv.controller.request.MovieRequest;
import com.tubetv.controller.response.CategoryResponse;
import com.tubetv.controller.response.MovieResponse;
import com.tubetv.controller.response.StreamingResponse;
import com.tubetv.entity.Category;
import com.tubetv.entity.Movie;
import com.tubetv.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {


    public static Movie toMovie(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();
        /*
        * Monta uma list de categories somente com os IDs da tabela category.
        * Assim podemos passar essa lista para o movieRequest transformar em um objeto tipo Movie puro.
        * */

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingID -> Streaming.builder().id(streamingID).build())
                .toList();



        return Movie
                .builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();


        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

}
