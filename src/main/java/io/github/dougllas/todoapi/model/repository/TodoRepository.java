package io.github.dougllas.todoapi.model.repository;

import io.github.dougllas.todoapi.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Iterable<Todo> findByEmail(String email);
    Optional<Todo> findByIdAndEmail(Long id, String email);

}
