package xyz.itbs.jokes.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.itbs.jokes.domain.Joke;
import xyz.itbs.jokes.services.JokeApiService;
import xyz.itbs.jokes.services.JokeService;

@Controller
public class JokeApiController {

    JokeApiService jokeApiService;
    JokeService jokeService;

    public JokeApiController(JokeApiService jokeApiService, JokeService jokeService) {
        this.jokeApiService = jokeApiService;
        this.jokeService = jokeService;
    }

    @GetMapping("/getjoke")
    public String initGetJokeForm(Model model) throws Exception{
        model.addAttribute("cats", jokeApiService.getCategories());
        return "apiform";
    }

    @PostMapping("/getjoke")
    public String saveJokeFromApi(@RequestParam("cat") String cat) throws Exception{
        Joke joke = jokeApiService.getJoke(cat);
        jokeService.saveJoke(joke);
        return "redirect:/";
    }
}
