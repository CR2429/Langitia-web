package com.annoapp.langitia.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Users")
public class User implements UserDetails{
    //propriete
    @Id
    @Column(name = "Username")
    private String Username;
    @Column(name = "Password")
    private String Password;
    @Column(name = "Status")
    private boolean Status;
    @Transient
    private List<GrantedAuthority> Roles;

    //constructeur
    public User() {}
    public User(String username, String password, List<GrantedAuthority> roles) {
        this.Username = username;
        this.Password = password;
        this.Status = true;
        this.Roles = roles;
    }

    //getter et setter
    @Override
    public String getPassword() {return this.Password;}
    @Override
    public String getUsername() {return this.Username;}
    public boolean getStatuts() {return this.Status;}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return this.Roles;}
    public void setRoles(List<GrantedAuthority> roles) {this.Roles = roles;}
    public void setPassword(String password) {this.Password = password;}

    //methode
    @Override
    public boolean isEnabled() {
        return this.getStatuts();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
