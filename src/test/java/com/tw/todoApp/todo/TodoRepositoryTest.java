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
    public void shouldAddTodoInRepository() throws TitleCanNotBeDuplicateException {
        TodoItem todoItem = new TodoItem("Modal", "read about angular modal", "2018-11-12");
        todoRepository.addTodoItem(todoItem);
        assertTrue(todoRepository.isExitByTodoTitle("Modal"));
    }

    @Test
    public void shouldThrowExceptionForAlreadyExistedTodoBySameTitle() throws TitleCanNotBeDuplicateException {
        this.thrown.expect(TitleCanNotBeDuplicateException.class);
        this.thrown.expectMessage("Title can not duplicate .This title already added");
        TodoItem firstTodoItem = new TodoItem("hello", "How r u", "2018-11-12");
        TodoItem secondTodoItem = new TodoItem("hello", "How r u", "2018-11-12");
        todoRepository.addTodoItem(firstTodoItem);
        todoRepository.addTodoItem(secondTodoItem);
    }

}
