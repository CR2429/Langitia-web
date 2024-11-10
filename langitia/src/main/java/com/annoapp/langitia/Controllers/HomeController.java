package com.annoapp.langitia.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class HomeController {

   
    

    @GetMapping("/")
    public ModelAndView acceuil() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
