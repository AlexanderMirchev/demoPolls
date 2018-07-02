package com.example.demoPolls.Services.base;

import com.example.demoPolls.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.InvalidObjectException;

//Interface for service with users
public interface UsersService extends UserDetailsService {

    User getUserByUsername(String username);

    void create(User user) throws InvalidObjectException;
}
