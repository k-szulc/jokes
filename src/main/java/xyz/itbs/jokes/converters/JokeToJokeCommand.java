package xyz.itbs.jokes.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import xyz.itbs.jokes.commands.JokeCommand;
import xyz.itbs.jokes.domain.Joke;

@Component
public class JokeToJokeCommand implements Converter <Joke, JokeCommand> {
    @Nullable
    @Override
    public JokeCommand convert(Joke source) {
        return JokeCommand.builder()
                .id(source.getId())
                .category(source.getCategory())
                .title(source.getTitle())
                .text(source.getText())
                .build();
    }
}
