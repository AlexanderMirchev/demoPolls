package com.example.demoPolls.Validators;

import com.example.demoPolls.Entities.User;
import com.example.demoPolls.Validators.base.Validator;

public class UserValidator implements Validator<User> {
    private final int MIN_USERNAME_LENGTH = 3;
    private final int MAX_USERNAME_LENGTH = 20;
    private final int MIN_PASSWORD_LENGTH = 6;
    private final int MAX_PASSWORD_LENGTH = 20;
//
//    public UserValidator (){}
//    @Autowired
//    private GenericRepository<User> usersRepository;

    @Override
    public boolean isValid(User user) {
        return isUsernameValid(user.getUsername()) &&
                isPasswordValid(user.getPassword());
    }

    private boolean isUsernameValid(String username) {
//        List<String> usernames = new ArrayList<>();
//        usersRepository.getAll().forEach(user -> usernames.add(user.getUsername()));
//        if(usernames.contains(username)) {
//            return false;
//        }
        return username != null &&
                username.length() >= MIN_USERNAME_LENGTH &&
                username.length() <= MAX_USERNAME_LENGTH;

    }

    private boolean isPasswordValid(String password) {
        return password != null &&
                password.length() >= MIN_PASSWORD_LENGTH &&
                password.length() <= MAX_PASSWORD_LENGTH;
    }

}
