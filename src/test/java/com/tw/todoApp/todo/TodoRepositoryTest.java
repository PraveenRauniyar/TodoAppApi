package com.tw.todoApp.todo;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
public class TodoRepositoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    TodoRepository todoRepository;


    @Test
    public void shouldAddTodoInRepository()  {
        TodoItem todoItem = new TodoItem("Modal", "read about angular modal", "2018-11-12");

        long id = todoRepository.addTodoItem(todoItem);
        assertTrue(todoRepository.isExitByTodoId(id));
    }

    @Test
    public void shouldReturnTrueForExistedId()  {
        TodoItem todoItem = new TodoItem("Preety", "read about angular modal", "2018-11-12");
        long id = todoRepository.addTodoItem(todoItem);
        assertTrue(todoRepository.isExitByTodoId(id));
    }

    @Test
    public void shouldReturnFalseForNonExistedId() {
        assertFalse(todoRepository.isExitByTodoId(-3));
    }

    @Test
    public void shouldDeleteTodoById() throws TodoNotFoundByThisIdException {
        TodoItem todoItem = new TodoItem("Cricket", "play cricket", "2018-11-09");
        long id = todoRepository.addTodoItem(todoItem);
        assertTrue(todoRepository.isExitByTodoId(id));
        todoRepository.deleteTodoByTodoId(id);
        assertFalse(todoRepository.isExitByTodoId(id));
    }

    @Test
    public void shouldThrowExceptionForIdNotFoundForDelete() throws TodoNotFoundByThisIdException {
        this.thrown.expect(TodoNotFoundByThisIdException.class);
        this.thrown.expectMessage( "Todo  not found  by this id in Todo List");
        todoRepository.deleteTodoByTodoId(0);
    }
}
