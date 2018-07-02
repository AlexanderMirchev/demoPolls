package com.example.demoPolls.Services;


import com.example.demoPolls.Entities.User;
import com.example.demoPolls.Repositories.base.GenericRepository;
import com.example.demoPolls.Services.base.UsersService;
import com.example.demoPolls.Validators.base.Validator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;

@Service
public class UsersServiceImpl implements UsersService {

    private final GenericRepository<User> usersRepository;
    private final PasswordEncoder passwordEncoder;
    private Validator<User> userValidator;


    public UsersServiceImpl(GenericRepository<User> usersRepository,
                            PasswordEncoder passwordEncoder,
                            Validator<User> userValidator) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
    }

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.getAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        return user;
    }

    @Override
    public void create(User user) throws InvalidObjectException {
        if(!userValidator.isValid(user)) {
            throw new InvalidObjectException("Invalid username or password");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
