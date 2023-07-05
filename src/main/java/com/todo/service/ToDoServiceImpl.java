package com.todo.service;

import com.todo.model.ToDoItem;
import com.todo.model.ToDoPriorityComparator;
import com.todo.parser.ToDoParser;
import com.todo.reader.ToDoReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ToDoServiceImpl implements ToDoService {
    private ToDoParser parser;
    private ToDoReader reader;
    private List<ToDoItem> toDoItems = new ArrayList<>();

    @PostConstruct
    public void init() {
        List<String> toDos = reader.readToDos();
        toDos.stream().forEach(input -> {
            ToDoItem toDoItem = parser.parse(input);
            if (toDoItem != null)
                toDoItems.add(toDoItem);
        });
    }

    public ToDoParser getParser() {

        return parser;
    }

    @Autowired
    public void setParser(ToDoParser parser) {

        this.parser = parser;
    }

    public ToDoReader getReader() {
        return reader;
    }

    @Autowired
    public void setReader(ToDoReader reader) {

        this.reader = reader;
    }


    public List<ToDoItem> getToDos(Optional<String> project, Optional<String> context, Optional<Boolean> sorted) {

        Stream<ToDoItem> toDoStream = toDoItems.stream();
        if (project.isPresent())
            toDoStream = toDoStream.filter(todo -> todo.getProjects().contains(project.get()));
        if (context.isPresent())
            toDoStream = toDoStream.filter(todo -> todo.getContexts().contains(context.get()));
        if (sorted.isPresent() && sorted.get())
            toDoStream = toDoStream.sorted(new ToDoPriorityComparator());
        return toDoStream.collect(Collectors.toList());
    }


    @Override
    public Optional<ToDoItem> getToDosById(int id) {

        return toDoItems.stream().filter(t -> t.getId() == id).findFirst();
    }
}
