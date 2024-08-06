package com.annoapp.langitia.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyErrorController{

    @ExceptionHandler(Exception.class)
    public ModelAndView mafonction(Exception exception, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("sys/error");
            mv.addObject("message",exception.getMessage());
            mv.addObject("error", exception.getStackTrace()[0]);
        return mv;
    }

}

