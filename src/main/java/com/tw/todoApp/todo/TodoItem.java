package com.tw.todoApp.todo;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Validated
public class TodoItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long todoId;

    @NotNull(message = "Title can not be null.")
    private String todoTitle;

    private String description;

    @NotNull(message = "Reminder can not be null.")
    private String reminder;

    public TodoItem(){}


    public TodoItem(@NotNull(message = "Title can not be null.") String todoTitle, String description, @NotNull(message = "Reminder can not be null.") String reminder) {
        this.todoTitle = todoTitle;
        this.description = description;
        this.reminder = reminder;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoItem)) return false;
        TodoItem todoItem = (TodoItem) o;
        return Objects.equals(getTodoTitle(), todoItem.getTodoTitle()) &&
                Objects.equals(getDescription(), todoItem.getDescription()) &&
                Objects.equals(getReminder(), todoItem.getReminder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTodoTitle(), getDescription(), getReminder());
    }
}
