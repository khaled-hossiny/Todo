package com.todo.model;

import java.util.Comparator;

public class ToDoPriorityComparator implements Comparator<ToDoItem> {
    @Override
    public int compare(ToDoItem o1, ToDoItem o2) {
        if (o1.getPriority() == null && o2.getPriority() == null)
            return 0;
        if (o1.getPriority() != null && o2.getPriority() == null)
            return -1;
        if (o1.getPriority() == null && o2.getPriority() != null)
            return 1;
        return o1.getPriority().compareTo(o2.getPriority());
    }

    public static void main(String[] args) {
        System.out.println("".compareTo("0"));
        System.out.println("a".compareTo(null));
    }
}
