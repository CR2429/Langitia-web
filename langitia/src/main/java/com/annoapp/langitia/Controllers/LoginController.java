package com.annoapp.langitia.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.annoapp.langitia.Authentification.MyAuthenticationProvider;
import com.annoapp.langitia.Authentification.MyUserManager;
import com.annoapp.langitia.Data.LoginDbContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
    //propriete
    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;
    @Autowired
    MyUserManager myUserManager;
    @Autowired
    SecurityContextRepository securityContextRepository;
    @Autowired
    LoginDbContext loginDbContext;

    //methode
    @GetMapping("/")
    public ModelAndView getMethodName(@RequestParam(value="loginError",required=false,defaultValue="false") String loginError) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
    
}
