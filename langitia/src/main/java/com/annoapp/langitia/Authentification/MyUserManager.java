package com.annoapp.langitia.Authentification;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.core.Authentication;
import com.annoapp.langitia.Data.LoginDbContext;
import com.annoapp.langitia.Models.User;

public class MyUserManager implements UserDetailsManager {
    //propriete
    private LoginDbContext loginDbContext;
    private PasswordEncoder passwordEncoder;

    //constructeur
    public MyUserManager(LoginDbContext loginDbContext, PasswordEncoder passwordEncoder) {
        this.loginDbContext = loginDbContext;
        this.passwordEncoder = passwordEncoder;
    }

    //getter et setter

    //methode
    /** Recuperer les UserDetails s'il existe */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDetails user = this.loginDbContext.getUserDetails(username);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    /** Creation d'un nouveau user */
    @Override
    public void createUser(UserDetails userDetails) {
        //recuperer un user
        User user = this.loginDbContext.getUserById(userDetails.getUsername());
        
        //test si l'email est deja existant
        if(this.loginDbContext.userExist(user.getUsername())) {
            throw new IncorrectResultSizeDataAccessException("Nom d'utilisateur deja utiliser",1);
        }

        //verifie si le client a des roles
        if (user.getAuthorities().isEmpty()) {
            throw new IncorrectResultSizeDataAccessException("Le user doit avoir au moins un role",1);
        }

        //Crypter le mot de passe
        String encodedpassword = this.passwordEncoder.encode(user.getPassword());

        //sauvegarde de l'user
        user.setPassword(encodedpassword);
        this.loginDbContext.createUser(user);
    }

    /** modifier un user */
    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** supprimer un user */
    @Override
    public void deleteUser(String username) {
        User user = this.loginDbContext.getUserById(username);
        this.loginDbContext.deleteUser(user);
    }

    /** modifier un mot de passe */
    @Override
    public void changePassword(String oldpassword, String newpassword) {
        // lire l'usager connnecté
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.loginDbContext.getUserById(username);
        
        //erreur a gerer
        if (currentUser == null) {
            throw new IncorrectResultSizeDataAccessException("Pour changer le mot de passe tu dois etre connecté",1);
        }
        if (this.passwordEncoder.encode(oldpassword) != user.getPassword()) {
            throw new IncorrectResultSizeDataAccessException("Votre ancien mot de passe n'est pas valide",1);
        }

        
        this.loginDbContext.changePassword(username, newpassword);
    }

    @Override
    public boolean userExists(String username) {
        return this.loginDbContext.userExist(username);
    }
}
