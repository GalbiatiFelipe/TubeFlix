package com.tubetv.service;
import com.tubetv.entity.Category;
import com.tubetv.entity.Movie;
import com.tubetv.entity.Streaming;
import com.tubetv.repository.CategoryRepository;
import com.tubetv.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));//passando o metodo para ele detectar a categoria que foi passada
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long movieId, Movie movie) {
        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()) {
            List<Category> categories = this.findCategories(movie.getCategories());
            List<Streaming> streamings = this.findStreamings(movie.getStreamings());

            Movie movieUpdated = optMovie.get(); // caso exista esse filme no BD, o opt sera alimentado com todos os atributos para fazer o update
            movieUpdated.setTitle(movie.getTitle());
            movieUpdated.setDescription(movie.getDescription());
            movieUpdated.setReleaseDate(movie.getReleaseDate());
            movieUpdated.setRating(movie.getRating());

            movieUpdated.getCategories().clear();
            movieUpdated.getCategories().addAll(categories);

            movieUpdated.getStreamings().clear();
            movieUpdated.getStreamings().addAll(streamings);

            movieRepository.save(movieUpdated);
            return Optional.of(movieUpdated);
            // No fim ele salva o que foi mudado e retorno como optional

        }
        return Optional.empty();
    }

    public List<Movie> findByCategory(Long category) {
        return movieRepository.findMovieByCategoriesId(category);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        for (Category category : categories) {
            categoryService.findCategoryById(category.getId()).ifPresent(categoriesFound::add);
        }
        return categoriesFound;
        /*
        * Este metodo vai garantir que o ID da category passada na coluna 'categories' na tabela 'movie' exista na tabela 'category'
        * criando um list de category, iterando sobre toda a lista passada como parametro e testando se ela esta presente, caso esteja a lista categoriesFound
        * será alimentada
        * */
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingFound = new ArrayList<>();
        for (Streaming streaming : streamings) {
            streamingService.findById(streaming.getId()).ifPresent(streamingFound::add);
        }
        return streamingFound;
    }

}
