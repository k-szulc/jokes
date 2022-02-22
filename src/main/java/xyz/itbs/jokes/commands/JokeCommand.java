package xyz.itbs.jokes.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.itbs.jokes.domain.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JokeCommand {
    private Long id;
    @NotBlank
    @Size(min = 1, max = 255)
    private String title;
    @NotBlank
    private String text;
    private Category category;
}
