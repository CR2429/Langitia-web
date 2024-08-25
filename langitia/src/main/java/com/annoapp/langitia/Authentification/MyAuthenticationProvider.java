package com.annoapp.langitia.Authentification;

import java.util.List;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.annoapp.langitia.Models.User;

import org.springframework.security.core.Authentication;


public class MyAuthenticationProvider implements AuthenticationProvider{
    //propriete
    private MyUserManager userManager;

    //constructeur
    public MyAuthenticationProvider(MyUserManager userManager) {
        this.userManager = userManager;
    }

    //getter et setter
    public void setUserManager(MyUserManager myUserManager) {
        this.userManager = myUserManager;
    }

    //methode
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = (User) this.userManager.loadUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("L'utilisateur n'existe pas");
        }

        if (!this.userManager.getPasswordEncoder().matches(password,user.getPassword())) {
            throw new BadCredentialsException("Email ou mot de passe incorrect");
        }

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            throw new BadCredentialsException("Erreur de connexion. Veuillez ressayer ou contacter l'administrateur");
        }

        authentication = new UsernamePasswordAuthenticationToken(user, password, authorities);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
