package com.todo.parser;

import com.todo.date.DateFormats;
import com.todo.date.DateUtility;
import com.todo.model.ToDoItem;
import com.todo.pattern.ToDoRegexPatterns;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ToDoRegexParser implements ToDoParser {
    @Override
    public ToDoItem parse(String entry) {
        Pattern pattern = Pattern.compile(ToDoRegexPatterns.TODO_PATTERN);
        Matcher matcher = pattern.matcher(entry);
        if (matcher.find()) {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setCompleted(matcher.group(1) != null);
            toDoItem.setPriority(matcher.group(2));
            if (matcher.group(3) != null)
                toDoItem.setCompletionDate(DateUtility.stringToDate(matcher.group(3), DateFormats.YYYY_MM_DD));
            if (matcher.group(4) != null)
                toDoItem.setCreationDate(DateUtility.stringToDate(matcher.group(4), DateFormats.YYYY_MM_DD));
            toDoItem.setContent(matcher.group(5));

            pattern = Pattern.compile(ToDoRegexPatterns.CONTEXT_PATTERN);
            matcher = pattern.matcher(toDoItem.getContent());
            while (matcher.find()) {
                toDoItem.getContexts().add(matcher.group(1).substring(1));
            }

            pattern = Pattern.compile(ToDoRegexPatterns.PROJECT_PATTERN);
            matcher = pattern.matcher(toDoItem.getContent());
            while (matcher.find()) {
                toDoItem.getProjects().add(matcher.group(1).substring(1));
            }

            pattern = Pattern.compile(ToDoRegexPatterns.META_DATA_PATTERN);
            matcher = pattern.matcher(toDoItem.getContent());
            while (matcher.find()) {
                toDoItem.getMetadata().add(matcher.group(1).trim());
            }
            return toDoItem;

        }
        return null;
    }
}
