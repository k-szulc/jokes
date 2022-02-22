package xyz.itbs.jokes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import xyz.itbs.jokes.commands.JokeCommand;
import xyz.itbs.jokes.domain.Joke;

@Component
public class JokeCommandToJoke implements Converter<JokeCommand, Joke> {

    @Nullable
    @Override
    public Joke convert(JokeCommand source) {
        return Joke.builder()
                .id(source.getId())
                .title(source.getTitle())
                .text(source.getText())
                .category(source.getCategory())
                .build();
    }
}
