package com.tubetv.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(Long id,
                            @Schema(type = "string", description = "Nome do filme")
                            String title,
                            @Schema(type = "string", description = "Descrição do filme")
                            String description,
                            //annotation que altera o padrao da forma de se escrever datas
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,
                            double rating,
                            List<CategoryResponse> categories,
                            List<StreamingResponse> streamings) {
}
