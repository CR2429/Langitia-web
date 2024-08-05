package com.annoapp.langitia.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class home extends HttpServlet {
    
    @RequestMapping(value = "/")
    public ModelAndView get() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }
}
