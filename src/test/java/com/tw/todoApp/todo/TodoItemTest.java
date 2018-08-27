package com.tw.todoApp.todo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TodoItemTest {

    @Autowired
    private TestEntityManager entityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldAddTodoWithNotNullTodoTitleAndReminder() {
        String date = "08/11/2017";
        TodoItem todo = new TodoItem("Read Angular", "Get Basic knowledge of angular",date);
        TodoItem addedTodo = this.entityManager.persistAndFlush(todo);
        assertThat(addedTodo.getTodoTitle(), is("Read Angular"));
        assertThat(addedTodo.getDescription(), is("Get Basic knowledge of angular"));
        assertThat(addedTodo.getReminder(), is(date));
    }

    @Test
    public void shouldThrowExceptionForInsertingNullTitle() {
        this.thrown.expect(ConstraintViolationException.class);
        TodoItem todo = new TodoItem(null,"nothing","2018-12-22");
        TodoItem addedTodoItem = this.entityManager.persistAndFlush(todo);
    }

    @Test
    public void shouldThrowExceptionForInsertingNullReminder() {
        this.thrown.expect(ConstraintViolationException.class);
        TodoItem todoItem = new TodoItem("Hi", "not getting time", null);
        TodoItem addedTodoItem = this.entityManager.persistAndFlush(todoItem);
    }
}