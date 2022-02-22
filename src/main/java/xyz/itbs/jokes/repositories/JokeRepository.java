package xyz.itbs.jokes.repositories;

import org.springframework.data.repository.CrudRepository;
import xyz.itbs.jokes.domain.Joke;

public interface JokeRepository extends CrudRepository<Joke, Long> {
}
