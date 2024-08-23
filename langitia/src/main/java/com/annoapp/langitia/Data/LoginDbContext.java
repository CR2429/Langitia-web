package com.annoapp.langitia.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.jdbc.core.RowMapper;

import com.annoapp.langitia.Models.IUserRepository;
import com.annoapp.langitia.Models.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginDbContext {
    //propriete
    private IUserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    //constructeur
    public LoginDbContext(IUserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    //getter et setter
    public void setUserRepository(IUserRepository userRepository) {this.userRepository = userRepository;}

    //methode
    /** Recupere la lioste de tout les users */
    public List<User> selectAll() {return this.userRepository.findAll();}

    /** Trouver un user specifique */
    public User selectUserById(String username) {
        User user = this.userRepository.findById(username).get();
        user.setRoles(this.getAuthorities(username));
        return user;
    }

    /** Recupere les roles d'un user specifique */
    @SuppressWarnings("deprecation")
    public List<GrantedAuthority> getAuthorities(String username) {
        String sql = "select role from Roles where username = ?";
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>)jdbcTemplate.query(sql, new String[]{username}, new RoleMapper());
        return grantedAuthorities;
    }

    /** Recupere le mode de passe d'un client */
    private String getPassword(String username) {
        return this.userRepository.findById(username).get().getPassword();
    }

    /** Ajout un user dans la base de donne */
    public void insertUser(User user) {
        this.userRepository.save(user);
    }

    /** Supprimer un user */

    //private class
    private class RoleMapper implements RowMapper<GrantedAuthority> {
        @SuppressWarnings("null")
        @Override
        public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SimpleGrantedAuthority(rs.getString("role"));
        }
    }
}
