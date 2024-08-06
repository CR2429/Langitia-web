package com.annoapp.langitia.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServlet;

import org.springframework.web.bind.annotation.GetMapping;




@Controller()
public class HomeController extends HttpServlet {
    
    @GetMapping("/")
    public ModelAndView acceuil() {
        ModelAndView mv = new ModelAndView("Acceuil");
        return mv;
    }
}
