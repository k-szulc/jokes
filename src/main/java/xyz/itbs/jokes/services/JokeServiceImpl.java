package xyz.itbs.jokes.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.itbs.jokes.commands.JokeCommand;
import xyz.itbs.jokes.converters.JokeCommandToJoke;
import xyz.itbs.jokes.converters.JokeToJokeCommand;
import xyz.itbs.jokes.domain.Joke;
import xyz.itbs.jokes.repositories.JokeRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class JokeServiceImpl implements JokeService {

    JokeRepository jokeRepository;
    JokeToJokeCommand jokeToJokeCommand;
    JokeCommandToJoke jokeCommandToJoke;

    public JokeServiceImpl(JokeRepository jokeRepository,
                           JokeToJokeCommand jokeToJokeCommand,
                           JokeCommandToJoke jokeCommandToJoke) {
        this.jokeRepository = jokeRepository;
        this.jokeToJokeCommand = jokeToJokeCommand;
        this.jokeCommandToJoke = jokeCommandToJoke;
    }

    @Override
    public Set<Joke> getAllJokes() {
        Set<Joke> jokeSet = new HashSet<>();
        jokeRepository.findAll().iterator().forEachRemaining(jokeSet::add);
        return jokeSet;
    }

    @Override
    public void deleteJokeById(Long id) {
        jokeRepository.deleteById(id);
    }

    @Override
    public JokeCommand saveJokeCommand(JokeCommand jokeCommand) {
        Joke savedJoke = jokeRepository.save(jokeCommandToJoke.convert(jokeCommand));
        return jokeToJokeCommand.convert(savedJoke);
    }
}
