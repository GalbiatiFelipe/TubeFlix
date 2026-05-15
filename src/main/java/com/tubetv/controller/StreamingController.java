package com.tubetv.controller;

import com.tubetv.controller.request.StreamingRequest;
import com.tubetv.controller.response.CategoryResponse;
import com.tubetv.controller.response.MovieResponse;
import com.tubetv.controller.response.StreamingResponse;
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

@Tag(name = "Streaming", description = "Recurso responsável por gerenciar a tabela de serviços de streaming no banco de dados")
public interface StreamingController {

    @Operation(summary = "Listar streamings", description = "Método responsável por listar todos os serviços de streaming cadastrados",
                security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Listar serviços de streaming",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    ResponseEntity<List<StreamingResponse>> findAll();


    @Operation(summary = "Salvar streamings", description = "Método responsável por listar todos os serviços de streaming cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Salvar serviço de streaming",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    ResponseEntity<StreamingResponse> saveCategory(@Valid @RequestBody StreamingRequest streamingRequest);


    @Operation(summary = "Buscar serviço de streaming por ID", description = "Método responsável por buscar os serviços de streaming pelo ID especifico.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Serviço de streaming encontrado",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @ApiResponse(responseCode = "404", description = "Serviço de streaming não encontrado", content = @Content())
    ResponseEntity<StreamingResponse> findById(@PathVariable Long id);


    @Operation(summary = "Deleta serviço de streaming por ID", description = "Método responsável por deletar um serviço de streaming do banco de dados de acordo com o ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "serviço de streaming deletado com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "serviço de streaming não encontrado", content = @Content())
    ResponseEntity<Void> deleteById(@PathVariable Long id);



}
