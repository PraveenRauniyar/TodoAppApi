package com.tw.todoApp.todo;

public class TodoNotFoundByThisIdException extends Throwable {

    public TodoNotFoundByThisIdException() {
        super("Todo  not found  by this id in Todo List");
    }
}
