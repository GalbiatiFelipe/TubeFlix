package com.tubetv.controller.request;

import jakarta.validation.constraints.NotBlank;
// annotation 'NotEmpty' diz que o atributo n deve ser passado vazio, a annotation 'Valid' deve ser colocada antes de 'RequestBody' no Controller referente.
public record CategoryRequest(@NotBlank(message = "Nome da categoria é obrigatório.") String name) {
}
