package com.example.apitodolist.service;

import com.example.apitodolist.entity.Todo;
import com.example.apitodolist.repository.TodoRepository;
import exception.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "priority")
                .and(Sort.by(Sort.Direction.ASC, "id"));
        return todoRepository.findAll(sort);
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return findAll();
    }

    public List<Todo> update(Long id, Todo todo) {
        todoRepository.findById(id).ifPresentOrElse(existingTodo -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            throw new BadRequestException("Id: " + id + " not found.");
        });
        return findAll();
    }

    public List<Todo> delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse(existingTodo -> {
            todoRepository.delete(existingTodo);
        }, () -> {
            throw new BadRequestException("Id: " + id + " not found.");
        });
        return findAll();
    }
}
