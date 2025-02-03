package com.annoapp.langitia.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/ouvrir-fenetre")
public class FenetreController {
    
    @GetMapping("/txt")
    public ModelAndView getMethodName() {
        ModelAndView mv = new ModelAndView("fenetre");
        mv.addObject("content", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        return mv;
    }
    
}
