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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
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
    public void shouldGiveEmptyListWhenNoTodoItemsAdded() {
        assertEquals(todoRepository.getAllTodoItems(),Collections.emptyList());
    }

    @Test
    public void shouldGiveAllTodoListWhenTodoItemsAlreadyAdded() throws TitleCanNotBeDuplicateException {
        TodoItem firstTodoItem = new TodoItem("Python", "read about python", LocalDate.now());
        TodoItem secondTodoItem = new TodoItem("Cricket", "practice of cricket", LocalDate.now());
        todoRepository.addTodoItem(firstTodoItem);
        todoRepository.addTodoItem(secondTodoItem);
        assertEquals(todoRepository.getAllTodoItems(),Arrays.asList(firstTodoItem,secondTodoItem));

    }

    @Test
    public void shouldAddTodoInRepository() throws TitleCanNotBeDuplicateException {
        TodoItem todoItem = new TodoItem("Modal", "read about angular modal", LocalDate.now());
        todoRepository.addTodoItem(todoItem);
        assertTrue(todoRepository.isExitByTodoTitle("Modal"));
    }

    @Test
    public void shouldThrowExceptionForAlreadyExistedTodoBySameTitle() throws TitleCanNotBeDuplicateException {
        this.thrown.expect(TitleCanNotBeDuplicateException.class);
        this.thrown.expectMessage("Title can not duplicate .This title already added");
        TodoItem firstTodoItem = new TodoItem("hello", "How r u", LocalDate.now());
        TodoItem secondTodoItem = new TodoItem("hello", "How r u", LocalDate.now());
        todoRepository.addTodoItem(firstTodoItem);
        todoRepository.addTodoItem(secondTodoItem);
    }
}
