package com.example.apitodolist;

import com.example.apitodolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class ApiTodolistApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCreateSuccess() {
        Todo todo = new Todo("todo1", "desctodo", false, 3);

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo(todo.getName())
                .jsonPath("$[0].description").isEqualTo(todo.getDescription())
                .jsonPath("$[0].priority").isEqualTo(todo.getPriority())
                .jsonPath("$[0].completed").isEqualTo(todo.getCompleted());
    }

    @Test
    public void testCreateTodoFailure() {
        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(
                        new Todo("", "", false, 0)
                ).exchange()
                .expectStatus().isBadRequest();
    }
}
