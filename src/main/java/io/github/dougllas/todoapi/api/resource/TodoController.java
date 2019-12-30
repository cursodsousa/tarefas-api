package io.github.dougllas.todoapi.api.resource;

import io.github.dougllas.todoapi.model.entity.Todo;
import io.github.dougllas.todoapi.model.repository.TodoRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("tarefas")
@RequiredArgsConstructor
@ApiModel("Todo API")
public class TodoController {

    private final TodoRepository repository;

    @GetMapping
    @ApiOperation(value = "Lista tarefas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public Iterable<Todo> list( @ApiParam("Email do cliente") @RequestHeader("x-tenant-id") String email ){
        return repository.findByEmail(email);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Salvar uma tarefa", code = 204)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Item salvo com sucesso")
    })
    public Todo save(
            @ApiParam("Tarefa")
            @RequestBody TodoDTO tarefa,
            @ApiParam("Email do cliente")
            @RequestHeader("x-tenant-id") String email ){
        Todo todo = Todo.builder()
                    .email(email).categoria(tarefa.getCategoria()).descricao(tarefa.getDescricao())
                    .build();
        return repository.save(todo);
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Atualizar para tarefa realizada", code = 204)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Item atualizado com sucesso"),
            @ApiResponse(code = 404, message = "Item não encontrado para o id e email do cliente")
    })
    public void done(
            @ApiParam("id da entidade")
            @PathVariable Long id ,
            @ApiParam("Email do cliente")
            @RequestHeader("x-tenant-id") String email ){
        repository.findByIdAndEmail( id , email).map( todo -> {
                    todo.setDone(true);
                    return repository.save(todo);
        }).orElseThrow( () -> new ResponseStatusException(NOT_FOUND) );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Deletar uma tarefa", code = 204)
    @ApiResponses({
            @ApiResponse(code = 204, message = "Item deletado com sucesso"),
            @ApiResponse(code = 404, message = "Item não encontrado para o id e email do cliente")
    })
    public void delete(
            @ApiParam("id da entidade")
            @PathVariable Long id ,
            @ApiParam("Email do cliente")
            @RequestHeader("x-tenant-id") String email){
        repository.findByIdAndEmail( id , email)
                .map( todo -> { repository.delete(todo); return Void.TYPE; } )
                .orElseThrow(() -> new ResponseStatusException( NOT_FOUND ) );
    }
}
