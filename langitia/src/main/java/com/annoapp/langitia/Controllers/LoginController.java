package com.annoapp.langitia.Controllers;

import java.net.Authenticator;
import java.sql.Savepoint;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.savedrequest.SavedRequest;
import com.annoapp.langitia.Authentification.MyAuthenticationProvider;
import com.annoapp.langitia.Authentification.MyUserManager;
import com.annoapp.langitia.Data.LoginDbContext;
import com.annoapp.langitia.Models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @GetMapping("/login")
    public ModelAndView getMethodName(@RequestParam(value="loginError",required=false,defaultValue="false") String loginError) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        try {
            //recuperer les info du formulaire de login
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            //tenter de creer le user
            User user = new User(username,password,new ArrayList<>());
            Authentication token = new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());

            //Valider le token
            Authentication authentication = myAuthenticationProvider.authenticate(token);
            securityContext.setAuthentication(authentication);
            securityContextRepository.saveContext(securityContext, request, response);

            //redirection
            SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
            if (savedRequest == null)
                return "redirect:/";
            else
                return "redirect:/"+savedRequest.getRedirectUrl();
        } catch (AuthenticationException ex) {
            HttpSession session=request.getSession(true);
            session.setAttribute("LoginErrorMessage", ex.getMessage());
            return "redirect:/login/login?loginError=true";
        } catch(Exception ex){
            HttpSession session=request.getSession(true);
            session.setAttribute("LoginErrorMessage", ex.getMessage());
            return "redirect:/login/login?loginError=true";
        }
    }
    
}
