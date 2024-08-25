package com.annoapp.langitia.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.jdbc.core.RowMapper;

import com.annoapp.langitia.Models.IUserRepository;
import com.annoapp.langitia.Models.User;


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
    /** Recupere la liste de tout les users */
    public List<User> selectAll() {
        return this.userRepository.findAll();
    }

    /** Trouver un user specifique */
    public User getUserById(String username) {
        User user = this.userRepository.findById(username).get();
        user.setRoles(this.getAuthorities(username));
        return user;
    }

    /** Recupere les roles d'un user specifique */
    public List<GrantedAuthority> getAuthorities(String username) {
        String sql = "SELECT role FROM Roles WHERE username = ?";
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>)jdbcTemplate.query(sql, new String[]{username}, new RoleMapper());
        return grantedAuthorities;
    }

    /** Recupere le mode de passe d'un user */
    private String getPassword(String username) {
        return this.userRepository.findById(username).get().getPassword();
    }

    /** Ajout un user dans la base de donne */
    public void insertUser(User user) {
        //ajouter un user
        this.userRepository.save(user);
    }

    /** Modifier un user */
    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    /** Supprimer un user */
    public void deleteUser(User user) {
        String username = user.getUsername();

        //suppression des roles du user
        String sql = "DELETE FROM Roles WHERE username = ?";
        this.jdbcTemplate.update(sql,username);

        //suppression du user
        this.userRepository.deleteById(username);
    }

    /** Observe la presence d'un user */
    public boolean userExist(String username) {
        boolean exist = this.userRepository.existsById(username);
        return exist;
    }

    /** Changer juste le mot de passe */
    public void changePassword(String username, String password) {
        String sql = "UPDATE Users SET password = ? WHERE username = ?";
        this.jdbcTemplate.update(sql,username,password);
    }

    /** Recuperer les UserDetails s'il existe */
    public UserDetails getUserDetails(String username) {
        if (userExist(username)) {
            return getUserById(username);
        } else {
            return null;
        }
    }

    /** Creer un nouvel user */
    public void createUser(User user) {
        //ajouter le user dans la base de donne
        this.userRepository.save(user);

        //ajout des roles lier au user
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String sql = "INSERT INTO Roles VALUES(?,?)";
            this.jdbcTemplate.update(sql, user.getUsername(), authority);
        }
    }

    //private class
    private class RoleMapper implements RowMapper<GrantedAuthority> {
        @SuppressWarnings("null")
        @Override
        public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SimpleGrantedAuthority(rs.getString("role"));
        }
    }
}
