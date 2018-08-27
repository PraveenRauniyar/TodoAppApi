package com.tw.todoApp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoRepository {

    @Autowired
    private Repository repository;


    public long addTodoItem(TodoItem todoItem)  {
        repository.save(todoItem);
        return todoItem.getTodoId();
    }

    public Boolean isExitByTodoId(long id) {
        return repository.existsById(id);
    }

    public List<TodoItem> getAllTodoItems() {
        return repository.findAll();
    }

    public void deleteTodoByTodoId(long id) throws  TodoNotFoundByThisIdException {
        if (!isExitByTodoId(id)){
            throw new TodoNotFoundByThisIdException();
        }
        repository.deleteById(id);
    }

}
