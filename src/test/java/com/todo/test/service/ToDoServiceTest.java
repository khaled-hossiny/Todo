package com.todo.test.service;

import com.todo.app.ToDoApp;
import com.todo.model.ToDoItem;
import com.todo.service.ToDoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ToDoApp.class)
public class ToDoServiceTest {
    @Autowired
    private ToDoService toDoService;

    @Test
    public void contextLoads() {
        Assertions.assertThat(toDoService).isNotNull();
    }

    @Test
    public void loadAllTodos() {
        List<ToDoItem> todos = toDoService.getToDos(Optional.empty(), Optional.empty(), Optional.empty());
        Assertions.assertThat(todos.size()).isEqualTo(20);
    }

    @Test
    public void projectFilter() {
        List<ToDoItem> caseStudyTodos = toDoService.getToDos(Optional.of("casestudy"), Optional.empty(), Optional.empty());
        caseStudyTodos.stream().map(todo -> todo.getProjects()).flatMap(projects -> projects.stream())
                .forEach(project -> Assertions.assertThat(project).isEqualTo("casestudy"));
    }

    @Test
    public void sortByPriority() {
        List<ToDoItem> sortedTodos = toDoService.getToDos(Optional.empty(), Optional.empty(), Optional.of(true));
        int i = 0;
        int j = 1;
        while (j < sortedTodos.size()) {
            ToDoItem todo1 = sortedTodos.get(i);
            ToDoItem todo2 = sortedTodos.get(j);
            if (todo1.getPriority() != null && todo2.getPriority() != null)
                Assertions.assertThat(todo1.getPriority().compareTo(todo2.getPriority())).isLessThanOrEqualTo(0);
            if (todo1.getPriority() == null && todo2.getPriority() != null)
                Assertions.fail("todos with no priority can't appear before todos with priority");
            i++;
            j++;
        }
    }
}
