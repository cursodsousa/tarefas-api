package io.github.dougllas.todoapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.dougllas.todoapi.model.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String descricao;

    private boolean done;

    private Categoria categoria;

}
