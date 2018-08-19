package com.tw.todoApp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    TodoRepository todoRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST, value = "/addTodo")
    public void addTodo(@RequestBody TodoItem todoItem) throws TitleCanNotBeDuplicateException {
        todoRepository.addTodoItem(todoItem);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/")
    public List<TodoItem> getAllTodo(){
        return todoRepository.getAllTodoItems();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{title}")
    public void deleteTodo(@PathVariable String title) throws TitleNotFoundException {
        todoRepository.deleteTodoByTitle(title);
    }



}
