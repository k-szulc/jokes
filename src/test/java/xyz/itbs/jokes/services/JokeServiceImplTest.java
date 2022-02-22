package xyz.itbs.jokes.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import xyz.itbs.jokes.commands.JokeCommand;
import xyz.itbs.jokes.converters.JokeCommandToJoke;
import xyz.itbs.jokes.converters.JokeToJokeCommand;
import xyz.itbs.jokes.converters.JokeToJokeCommandTest;
import xyz.itbs.jokes.domain.Category;
import xyz.itbs.jokes.domain.Joke;
import xyz.itbs.jokes.repositories.JokeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JokeServiceImplTest {

    JokeServiceImpl jokeService;

    @Mock
    JokeRepository jokeRepository;
    @Mock
    JokeToJokeCommand jokeToJokeCommand;
    @Mock
    JokeCommandToJoke jokeCommandToJoke;

    Joke joke;
    JokeCommand jokeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        jokeService = new JokeServiceImpl(jokeRepository,jokeToJokeCommand,jokeCommandToJoke);
        joke = Joke.builder()
                .id(1L)
                .category(Category.ANIMAL)
                .title("foo")
                .text("bar")
                .build();
        jokeCommand = JokeCommand.builder()
                .id(1L)
                .category(Category.ANIMAL)
                .title("foo")
                .text("bar")
                .build();
    }

    @Test
    public void getAllJokes() {
        Set<Joke> mockSet = new HashSet<Joke>();
        mockSet.add(joke);
        when(jokeRepository.findAll()).thenReturn(mockSet);

        Set<Joke> jokes = jokeService.getAllJokes();
        assertEquals(jokes.size(),1);
        verify(jokeRepository,times(1)).findAll();

    }

    @Test
    public void deleteJokeById() {
        jokeService.deleteJokeById(1L);
        verify(jokeRepository,times(1)).deleteById(anyLong());
    }

    @Test
    public void saveJokeCommand() {
        when(jokeCommandToJoke.convert(any(JokeCommand.class))).thenReturn(joke);
        when(jokeToJokeCommand.convert(any(Joke.class))).thenReturn(jokeCommand);
        when(jokeRepository.save(any())).thenReturn(joke);

        jokeService.saveJokeCommand(jokeCommand);
        verify(jokeRepository,times(1)).save(any(Joke.class));
        verify(jokeCommandToJoke,times(1)).convert(any(JokeCommand.class));
        verify(jokeToJokeCommand,times(1)).convert(any(Joke.class));

    }

    @Test
    public void saveJoke() {
        when(jokeRepository.save(any())).thenReturn(joke);
        jokeService.saveJoke(joke);
        verify(jokeRepository,times(1)).save(any(Joke.class));

    }
}