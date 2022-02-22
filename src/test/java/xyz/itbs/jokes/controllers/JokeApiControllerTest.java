package xyz.itbs.jokes.controllers;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import xyz.itbs.jokes.services.JokeApiService;
import xyz.itbs.jokes.services.JokeService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class JokeApiControllerTest {

    JokeApiController controller;
    MockMvc mockMvc;

    @Mock
    JokeApiService jokeApiService;
    @Mock
    JokeService jokeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        controller = new JokeApiController(jokeApiService,jokeService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();

    }

    @Test
    public void initGetJokeForm() throws Exception{
        mockMvc.perform(get("/getjoke"))
                .andExpect(status().isOk())
                .andExpect(view().name("apiform"))
                .andExpect(model().attributeExists("cats"));
    }

    @Test
    public void initGetJokeForm_Failed() throws Exception{
        doThrow(new JSONException("foo")).when(jokeApiService).getCategories();
        mockMvc.perform(get("/getjoke"))
                .andExpect(status().isTooManyRequests())
                .andExpect(view().name("errors/429"));
    }

    @Test
    public void saveJokeFromApi() throws Exception{
        mockMvc.perform(post("/getjoke").
                        param("cat","")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void saveJokeFromApi_Failed() throws Exception{
        doThrow(new JSONException("foo")).when(jokeApiService).getJoke(anyString());
        mockMvc.perform(post("/getjoke")
                        .param("cat","")
                )
                .andExpect(status().isTooManyRequests())
                .andExpect(view().name("errors/429"));
    }
}