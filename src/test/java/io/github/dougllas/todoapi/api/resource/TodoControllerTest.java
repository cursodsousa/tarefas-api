package io.github.dougllas.todoapi.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dougllas.todoapi.model.entity.Todo;
import io.github.dougllas.todoapi.model.enums.Categoria;
import io.github.dougllas.todoapi.model.repository.TodoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

    public static final String URL = "/tarefas";
    MockMvc mockMvc;

    @Mock
    TodoRepository repository;

    @BeforeEach
    public void setUp(){
        TodoController todoController = new TodoController(repository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    void list() throws  Exception {
        BDDMockito.given( repository.findAll() ).willReturn(Collections.emptyList());
        this.mockMvc
                .perform(get("/tarefas"))
                .andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        String email = "email@dominio.com";
        Todo todo = new Todo(1l , email, "something to do", false, Categoria.ESTUDOS);

        BDDMockito.given(repository.save(Mockito.any(Todo.class))).willReturn(todo);
        String json = new ObjectMapper().writeValueAsString(todo);

        this.mockMvc.perform(
                     post(URL)
                    .header("x-tenant-id", email)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("descricao").value("something to do"))
                .andExpect(jsonPath("done").value(false))
                ;

    }

    @Test
    void done() throws Exception{
        String email = "email@dominio.com";

        Todo todo = new Todo(1l , email, "something to do", true, Categoria.ESTUDOS);
        BDDMockito.given( repository.save(Mockito.any(Todo.class)) ).willReturn(todo);

        this.mockMvc
                .perform( patch(URL.concat("/1")) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("done").value(true))

        ;
    }

    @Test
    void delete() {
    }
}