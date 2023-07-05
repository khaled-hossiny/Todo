package com.todo.test.web;

import com.todo.app.ToDoApp;
import com.todo.model.ToDoItem;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ToDoApp.class)
public class ToDoWebServiceTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @Test
    public void getAllToDosEndPoint() {
        List<ToDoItem> toDoItems = restTemplate.getForObject(String.format("http://localhost:%d/todo", port), List.class);
        Assertions.assertThat(toDoItems).isNotNull();
    }

    @Test
    public void getToDoByIdEndPoint() {
        ToDoItem toDoItem = restTemplate.getForObject(String.format("http://localhost:%d/todo/2", port), ToDoItem.class);
        Assertions.assertThat(toDoItem).isNotNull();
    }

    @Test
    public void getToDosReturnJSON() {
        String toDoItemsJSON = restTemplate.getForObject(String.format("http://localhost:%d/todo", port), String.class);
        try {
            new JSONArray(toDoItemsJSON);
        } catch (JSONException e) {
            Assertions.fail("returned response can't be parsed into JSON");
        }
    }
}
