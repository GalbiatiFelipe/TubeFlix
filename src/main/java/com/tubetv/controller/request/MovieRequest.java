package com.tubetv.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(@Schema(type = "string", description = "Nome do filme")
                           @NotBlank(message = "Nome do Filme é obrigatório.")
                           String title,
                           @Schema(type = "string", description = "Descrição do filme")
                           String description,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           @Schema(type = "date", description = "Data de lançamento do filme. Ex: 18/03/2004")
                           LocalDate releaseDate,
                           @Schema(type = "double", description = "Nota do filme. Ex: 7.5")
                           double rating,
                           @Schema(type = "array", description = "Lista de códigos de categoria")
                           List<Long> categories,
                           @Schema(type = "array", description = "Lista de códigos de serviços de streaming")
                           List<Long> streamings) {
}

/*
* @JsonFormat: annotation que altera o padrao da forma de se escrever datas.
*
* @NotBlank: mensagem de erro quando o espaço estiver em branco.
*
* @Schema: informações sobre os atributos para o swagger.
* */
