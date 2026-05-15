package com.tubetv.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
// annotation 'NotEmpty' diz que o atributo n deve ser passado vazio, a annotation 'Valid' deve ser colocada antes de 'RequestBody' no Controller referente.
public record CategoryRequest(@Schema(type = "string", description = "Nome da Categoria")
                              @NotBlank(message = "Nome da categoria é obrigatório.")
                              String name) {
}
