package xyz.itbs.jokes.services;

import xyz.itbs.jokes.domain.Joke;

import java.util.Set;

public interface JokeApiService {

    Set<String> getCategories() throws Exception;
    Joke getJoke(String cat) throws Exception;

}
