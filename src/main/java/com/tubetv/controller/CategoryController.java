package com.tubetv.controller;

import com.tubetv.controller.request.CategoryRequest;
import com.tubetv.controller.response.CategoryResponse;
import com.tubetv.controller.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Category", description = "Recurso responsável por gerenciar a tabela de categorias.")
public interface CategoryController {

    @Operation(summary = "Listar categorias", description = "Método responsavel por listar todas as categorias cadastradas.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Listando todos as categorias",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponse.class))))
    ResponseEntity<List<CategoryResponse>> findAllCategories();


    @Operation(summary = "Salvar categorias", description = "Método responsável por salvar novas categorias no banco de dados.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Categoria salva.",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class)))
    ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest);


    @Operation(summary = "Buscar categoria por ID", description = "Método responsável por buscar as categorias pelo ID especifico.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Categoria encontrada",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    ResponseEntity<CategoryResponse> findCategoryById(@PathVariable Long id);


    @Operation(summary = "Deleta categoria por ID", description = "Método responsável por deletar uma categoria do banco de dados de acordo com o ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada", content = @Content())
    ResponseEntity<Void> deleteCategory(@PathVariable Long id);

}
