package xyz.itbs.jokes.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.itbs.jokes.services.JokeService;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    JokeService jokeService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(jokeService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(indexController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    public void getIndexPage() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("jokes"));
    }

    @Test
    public void deleteJoke() throws Exception{
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void deleteJoke_NotFound() throws Exception{
        doThrow(new EmptyResultDataAccessException(1)).when(jokeService).deleteJokeById(anyLong());
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404"));
    }

    @Test
    public void deleteJoke_NaN() throws Exception{
        mockMvc.perform(get("/delete/foo"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404"));
    }

    @Test
    public void newJoke() throws Exception{
        mockMvc.perform(get("/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("jokeform"))
                .andExpect(model().attributeExists("jokeCommand"));

    }

    @Test
    public void saveJoke() throws Exception{
        mockMvc.perform(post("/new")
                .param("id","")
                .param("title", "foo")
                .param("text","bar")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

    }

    @Test
    public void saveJoke_ValidationError() throws Exception{
        mockMvc.perform(post("/new")
                        .param("id","")
                        .param("title", "")
                        .param("text","")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("jokeform"));
    }
}