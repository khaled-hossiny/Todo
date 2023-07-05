package com.todo.test.parser;

import com.todo.app.ToDoApp;
import com.todo.date.DateFormats;
import com.todo.date.DateUtility;
import com.todo.model.ToDoItem;
import com.todo.parser.ToDoParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ToDoApp.class)
public class ToDoParserTest {
    @Autowired
    ToDoParser toDoParser;

    @Test
    public void contextLoads() {
        Assertions.assertThat(toDoParser).isNotNull();
    }

    @Test
    public void completedTodo() {
        String input = "x 2049-06-29 2021-01-26 find this Todo.txt list online +casestudy type:inception";
        ToDoItem toDoItem = toDoParser.parse(input);
        Assertions.assertThat(toDoItem.isCompleted()).isTrue();
    }

    @Test
    public void toDoCreationDate() {
        String input = "x 2049-06-29 2021-01-26 find this Todo.txt list online +casestudy type:inception";
        ToDoItem toDoItem = toDoParser.parse(input);
        Assertions.assertThat(DateUtility.DateToString(toDoItem.getCreationDate(), DateFormats.YYYY_MM_DD)).isEqualTo("2021-01-26");
    }

    @Test
    public void toDoDueDate() {
        String input = "x 2049-06-29 2021-01-26 find this Todo.txt list online +casestudy type:inception";
        ToDoItem toDoItem = toDoParser.parse(input);
        Assertions.assertThat(DateUtility.DateToString(toDoItem.getCompletionDate(), DateFormats.YYYY_MM_DD)).isEqualTo("2049-06-29");
    }

    @Test
    public void toDoPriority() {
        String input = "(A) allow to get all TODOs via API +casestudy type:feature";
        ToDoItem toDoItem = toDoParser.parse(input);
        Assertions.assertThat(toDoItem.getPriority()).isEqualTo("(A)");
    }

    @Test
    public void toDoProject() {
        String input = "(A) allow to get all TODOs via API +casestudy type:feature";
        ToDoItem toDoItem = toDoParser.parse(input);
        Assertions.assertThat(toDoItem.getProjects().size()).isEqualTo(1);
        Assertions.assertThat(toDoItem.getProjects().get(0)).isEqualTo("casestudy");
    }

    @Test
    public void toDoContext() {
        String input = "(A) Thank Mom for the meatballs @phone";
        ToDoItem toDoItem = toDoParser.parse(input);
        Assertions.assertThat(toDoItem.getContexts().size()).isEqualTo(1);
        Assertions.assertThat(toDoItem.getContexts().get(0)).isEqualTo("phone");
    }

    @Test
    public void toDoMetaData() {
        String input = "(B) allow to filter TODOs by project via API +casestudy type:feature optional:true";
        ToDoItem toDoItem = toDoParser.parse(input);
        Assertions.assertThat(toDoItem.getMetadata().size()).isEqualTo(2);
        Assertions.assertThat(toDoItem.getMetadata().get(0)).isEqualTo("type:feature");
        Assertions.assertThat(toDoItem.getMetadata().get(1)).isEqualTo("optional:true");
    }
}
