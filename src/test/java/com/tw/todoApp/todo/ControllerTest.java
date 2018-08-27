package com.tw.todoApp.todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoRepository todoRepository;

    public static final org.springframework.http.MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Test
    public void shouldGiveAllTodoListOnHomePage() throws Exception {

        TodoItem firstTodoItem = new TodoItem("hi", "hello", "2018-02-12");
        TodoItem secondTodoItem = new TodoItem("not", "hello", "2018-02-12");
        List<TodoItem> todoItems = Arrays.asList(firstTodoItem, secondTodoItem);
        when(todoRepository.getAllTodoItems()).thenReturn(todoItems);
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].todoTitle", is("hi")))
                .andExpect(jsonPath("$[0].description", is("hello")))
                .andExpect(jsonPath("$[0].reminder", is("2018-02-12")))
                .andExpect(jsonPath("$[1].reminder", is("2018-02-12")));
        verify(todoRepository, times(1)).getAllTodoItems();
    }

    @Test
    public void shouldAddTodoWithPostRequest() throws  Exception {
        mockMvc.perform(post("/addTodo").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"todoTitle\": \"Hello\", \"description\":\"Nothing yaar\",\"reminder\": \"2009\"}"))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldSendBadStatusForBadRequest() throws  Exception {
        mockMvc.perform(post("/addTodo").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(""))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldSendOkRequestForDeleteTodoWhenTitleIsAvailable() throws Exception, TodoNotFoundByThisIdException {
        when(todoRepository.isExitByTodoId(1)).thenReturn(true);
        mockMvc.perform(delete("/delete/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
        verify(todoRepository, times(1)).deleteTodoByTodoId(1);

    }
}