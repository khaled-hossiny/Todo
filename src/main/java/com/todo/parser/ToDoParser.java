package com.todo.parser;

import com.todo.model.ToDoItem;

public interface ToDoParser {
    ToDoItem parse(String entry);
}
