package xyz.itbs.jokes.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.tool.schema.extract.spi.TableInformation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.itbs.jokes.domain.Category;
import xyz.itbs.jokes.domain.Joke;
import xyz.itbs.jokes.repositories.JokeRepository;

import javax.transaction.Transactional;

@Component
@Slf4j
public class JokesBootstrap implements CommandLineRunner {

    private final JokeRepository jokeRepository;

    public JokesBootstrap(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Joke joke1 = Joke.builder()
                .category(Category.BLONDE)
                .text("TextTextText")
                .title("TitleTitleTitle")
                .build();
        Joke joke2 = Joke.builder()
                .category(Category.ANIMAL)
                .text("TextAnimal")
                .title("Animal Joke")
                .build();
        Joke joke3 = Joke.builder()
                .category(Category.KNOCK_KNOCK)
                .text("TextKnockKnock")
                .title("KnockKnock")
                .build();

        jokeRepository.save(joke1);
        jokeRepository.save(joke2);
        jokeRepository.save(joke3);
    }
}
