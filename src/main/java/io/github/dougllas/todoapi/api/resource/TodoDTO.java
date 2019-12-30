package io.github.dougllas.todoapi.api.resource;

import io.github.dougllas.todoapi.model.enums.Categoria;
import lombok.Data;

@Data
public class TodoDTO {
    private String descricao;
    private Categoria categoria;
}
