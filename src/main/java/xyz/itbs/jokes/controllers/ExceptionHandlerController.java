package xyz.itbs.jokes.controllers;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ExceptionHandler(JSONException.class)
    public ModelAndView handleJSONException(Exception exception){
        log.error("Handling JSON creation exception");
        ModelAndView mav = new ModelAndView("errors/429");
        mav.addObject("exception",exception);
        return mav;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleNotFoundException(Exception exception){
        log.error("Handling joke not found exception");
        ModelAndView mav = new ModelAndView("errors/404");
        mav.addObject("exception",exception);
        return mav;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(java.lang.NumberFormatException.class)
    public ModelAndView handleNumberFormatException(Exception exception){
        log.error("Handling joke not found exception");
        ModelAndView mav = new ModelAndView("errors/404");
        mav.addObject("exception",exception);
        return mav;
    }
}
