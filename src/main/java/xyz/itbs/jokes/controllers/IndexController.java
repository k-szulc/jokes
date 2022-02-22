package xyz.itbs.jokes.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import xyz.itbs.jokes.commands.JokeCommand;
import xyz.itbs.jokes.services.JokeService;

import javax.validation.Valid;

@Slf4j
@Controller
public class IndexController {

    JokeService jokeService;

    public IndexController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("jokes",jokeService.getAllJokes());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteJoke(@PathVariable String id){
        jokeService.deleteJokeById(Long.valueOf(id));
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newJoke(Model model){
        model.addAttribute("jokeCommand", JokeCommand.builder().build());
        return "jokeform";
    }

    @PostMapping("/new")
    public String saveJoke(@Valid @ModelAttribute("jokeCommand") JokeCommand command,
                                     BindingResult bindingResult,
                                        Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> log.error(error.toString()));
            return "jokeform";
        }
        jokeService.saveJokeCommand(command);
        return "redirect:/";
    }



}
