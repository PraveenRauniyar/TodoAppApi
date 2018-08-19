package com.tw.todoApp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoRepository {

    @Autowired
    private Repository repository;


    public void addTodoItem(TodoItem todoItem) throws TitleCanNotBeDuplicateException {
        if (isExitByTodoTitle(todoItem.getTodoTitle())){
            throw new TitleCanNotBeDuplicateException();
        }
        repository.save(todoItem);
    }


    public Boolean isExitByTodoTitle(String title) {
        return repository.existsById(title);
    }

    public List<TodoItem> getAllTodoItems() {
        return repository.findAll();
    }

    public void deleteTodoByTitle(String title) throws TitleNotFoundException {
        if (!isExitByTodoTitle(title)){
            throw new TitleNotFoundException();
        }
        repository.deleteById(title);
    }
}
