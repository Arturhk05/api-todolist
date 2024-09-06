package com.example.apitodolist.controller;

import com.example.apitodolist.entity.Todo;
import com.example.apitodolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        List<Todo> list = todoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<List<Todo>> create(@Valid @RequestBody Todo todo) {
        List<Todo> list = todoService.create(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<List<Todo>> update(@PathVariable Long id ,@RequestBody Todo todo) {
        List<Todo> list = todoService.update(id, todo);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable Long id) {
        List<Todo> list = todoService.delete(id);
        return ResponseEntity.ok().body(list);
    }
}
