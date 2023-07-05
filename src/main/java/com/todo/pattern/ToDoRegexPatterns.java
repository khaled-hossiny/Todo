package com.todo.pattern;

public class ToDoRegexPatterns {
    private static final String DATE_PATTERN = "(\\d{4}-\\d{2}-\\d{2}\\s+)?";
    private static final String COMPLETED_PATTERN = "(x\\s+)?";
    private static final String PRIORITY_PATTERN = "(\\([A-Z]\\)\\s+)?";
    private static final String DESCRIPTION_PATTERN = "(.*)";
    public static final String TODO_PATTERN = COMPLETED_PATTERN + PRIORITY_PATTERN + DATE_PATTERN + DATE_PATTERN + DESCRIPTION_PATTERN;
    public static final String CONTEXT_PATTERN = ".*?(@\\S+).*?";
    public static final String META_DATA_PATTERN = ".*?(\\S+:\\S+).*?";
    public static final String PROJECT_PATTERN = ".*?(\\+\\S+).*?";
}
