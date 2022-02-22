package xyz.itbs.jokes.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.itbs.jokes.domain.Category;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JokeCommand {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    private Category category;
}
