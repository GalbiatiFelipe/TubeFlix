package com.tubetv.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StreamingResponse(Long id,
                                @Schema(type = "string", description = "Nome do serviço de streaming")
                                String name) {
}
