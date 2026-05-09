package com.tubetv.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(@NotBlank(message = "Nome do Filme é obrigatório.") String title,
                           String description,
                           //annotation que altera o padrao da forma de se escrever datas
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate releaseDate,
                           double rating,
                           List<Long> categories,
                           List<Long> streamings) {
}
