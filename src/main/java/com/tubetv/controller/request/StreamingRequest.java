package com.tubetv.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record StreamingRequest(@NotBlank(message = "Nome do serviço de Streaming é obrigatório.")
                               @Schema(type = "string", description = "Nome do serviço de streaming")
                               String name) {
}
