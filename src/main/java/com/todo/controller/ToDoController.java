package com.todo.controller;

import com.todo.model.ToDoItem;
import com.todo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("todo")
public class ToDoController {

    private ToDoService toDoService;

    @GetMapping(produces = "application/json")
    public List<ToDoItem> getAllToDos(@RequestParam(value = "project") Optional<String> project,
                                      @RequestParam(value = "context") Optional<String> context,
                                      @RequestParam(value = "sorted") Optional<Boolean> sorted) {
        return toDoService.getToDos(project, context, sorted);
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<ToDoItem> getToDosById(@PathVariable(value = "id") int id) {

        return toDoService.getToDosById(id);
    }

    public ToDoService getToDoService() {
        return toDoService;
    }

    @Autowired
    public void setToDoService(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
}
