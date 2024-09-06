package com.example.apitodolist;

import com.example.apitodolist.entity.Todo;

import java.util.ArrayList;
import java.util.List;

public class TestConstants {

    public static final List<Todo> TODOS = new ArrayList<>() {
        {
            add(new Todo(1L, "Artur", "Estudar", false, 1));
            add(new Todo(2L, "Artur", "Ler", true, 1));
            add(new Todo(3L, "Artur", "Estudar", false, 1));
            add(new Todo(4L, "Artur", "Lavar roupa", true, 1));
            add(new Todo(5L, "Artur", "Comer", false, 1));
        }
    };

    public static final Todo TODO = TODOS.get(0);
}
