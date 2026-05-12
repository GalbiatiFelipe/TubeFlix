package com.tubetv.controller;

import com.tubetv.controller.request.MovieRequest;
import com.tubetv.controller.response.MovieResponse;
import com.tubetv.entity.Movie;
import com.tubetv.mapper.MovieMapper;
import com.tubetv.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tubetv/movie")
@RequiredArgsConstructor
//annotation simples que mostra informações sobre a classe
@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento dos filmes.")
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "Listar filmes", description = "Método responsavel por listar todos os filmes cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Listando todos os filmes",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    /*
    * @SecurityRequirement: a configuração desta annotation esta na classe 'SwaggerConfig'
    *
    * @ArraySchema: como o retorno do endpoint é uma lista o Schema é alterado para ser um array.
    * */
    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        List<MovieResponse> movies = movieService.findAll().stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Salvar filme", description = "Método responsável para salvar um novo filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso",
           content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    /*
    * @Operation: informações básicas do endpoint.
    *
    * @ApiResponse: tipo de resposta e uma descrição do que ela faz.
    *        -->@Content: implementa os schemas definidos da raiz do tipo de objeto que o endpoint retorna.
    * */
    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(savedMovie));
    }

    @Operation(summary = "Buscar filme por ID", description = "Método responsável por procurar os filmes pelo ID especifico.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
         return movieService.findById(id)
                 .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                 .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar Filme", description = "Método responsável por atualizar dados do filme",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme alterado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest) {
        return movieService.update(id, MovieMapper.toMovie(movieRequest))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar filmes pela categoria", description = "Método responsavel por listar todos os filmes com base nas categorias",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme(s) encontrado(s)",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category) {
        return ResponseEntity.ok(movieService.findByCategory(category).stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @Operation(summary = "Deleta filme por ID", description = "Método responsável por deletar um filme do banco de dados de acordo com o ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Movie> movie = movieService.findById(id);
        if (movie.isPresent()) {
            movieService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

}
