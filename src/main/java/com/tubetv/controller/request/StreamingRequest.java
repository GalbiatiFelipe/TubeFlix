package com.tubetv.controller.request;

import jakarta.validation.constraints.NotBlank;

public record StreamingRequest(@NotBlank(message = "Nome do serviço de Streaming é obrigatório.") String name) {
}
