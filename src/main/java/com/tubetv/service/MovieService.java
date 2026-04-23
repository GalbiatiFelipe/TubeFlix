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
        * Este metodo vai garantir que o ID da categorie passada na coluna 'categories' na tabela 'movie' exista na tabela 'category'
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
