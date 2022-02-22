package xyz.itbs.jokes.converters;

import org.junit.Before;
import org.junit.Test;
import xyz.itbs.jokes.commands.JokeCommand;
import xyz.itbs.jokes.domain.Category;
import xyz.itbs.jokes.domain.Joke;

import static org.junit.Assert.*;

public class JokeCommandToJokeTest {

    JokeCommandToJoke converter;
    Joke joke;
    JokeCommand jokeCommand;


    @Before
    public void setUp() throws Exception {
        converter = new JokeCommandToJoke();
        jokeCommand = JokeCommand
                .builder()
                .id(1L)
                .category(Category.ANIMAL)
                .text("foo")
                .title("bar")
                .build();
    }

    @Test
    public void convert() {
        joke = converter.convert(jokeCommand);
        assertEquals(joke.getId(),jokeCommand.getId());
        assertEquals(joke.getCategory(),jokeCommand.getCategory());
        assertEquals(joke.getText(),jokeCommand.getText());
        assertEquals(joke.getTitle(),jokeCommand.getTitle());
    }
}