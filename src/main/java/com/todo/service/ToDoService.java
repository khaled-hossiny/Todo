package com.todo.service;

import com.todo.model.ToDoItem;

import java.util.List;
import java.util.Optional;

public interface ToDoService {
    Optional<ToDoItem> getToDosById(int id);

    List<ToDoItem> getToDos(Optional<String> project, Optional<String> context, Optional<Boolean> sorted);
}
