package xyz.itbs.jokes.services;

import xyz.itbs.jokes.commands.JokeCommand;
import xyz.itbs.jokes.domain.Joke;

import java.util.Set;

public interface JokeService {

    Set<Joke> getAllJokes();
    void deleteJokeById(Long id);
    JokeCommand saveJokeCommand(JokeCommand jokeCommand);

}
