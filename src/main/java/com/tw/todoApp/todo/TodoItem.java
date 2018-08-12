package com.tw.todoApp.todo;

import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Validated
public class TodoItem {

    @Id
    private String todoTitle;

    private String description;

    @NotNull(message = "Reminder can not be null.")
    private LocalDate reminder;

    public TodoItem(){}

    public TodoItem(String todoTitle, String description, LocalDate reminder) {
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

    public LocalDate getReminder() {
        return reminder;
    }

    public void setReminder(LocalDate reminder) {
        this.reminder = reminder;
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

    @Override
    public String toString() {
        return "TodoItem{" +
                "todoTitle='" + todoTitle + '\'' +
                ", description='" + description + '\'' +
                ", reminder='" + reminder + '\'' +
                '}';
    }
}
