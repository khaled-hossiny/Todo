package com.todo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoItem {
    private boolean completed;
    private String priority;
    private String content;
    private List<String> projects;
    private List<String> contexts;
    private List<String> metadata;
    private Date completionDate;
    private Date creationDate;
    private int id;
    private static int count = 1;

    public ToDoItem() {

        id = count++;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }

    public String getPriority() {

        return priority;
    }

    public void setPriority(String priority) {
        if (priority != null)
            this.priority = priority.trim();
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        if (content != null)
            this.content = content.trim();
    }

    public List<String> getProjects() {
        if (projects == null)
            projects = new ArrayList<>();
        return projects;
    }

    public List<String> getContexts() {
        if (contexts == null)
            contexts = new ArrayList<>();
        return contexts;
    }

    public Date getCompletionDate() {

        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {

        this.completionDate = completionDate;
    }

    public Date getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(Date creationDate) {

        this.creationDate = creationDate;
    }

    public int getId() {

        return id;
    }

    public List<String> getMetadata() {
        if (metadata == null)
            metadata = new ArrayList<>();
        return metadata;
    }
}
