package com.annoapp.langitia.Models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String>  {
    
}
