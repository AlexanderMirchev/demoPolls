package com.example.demoPolls.Web;

import com.example.demoPolls.Entities.User;
import com.example.demoPolls.Services.base.UsersService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.InvalidObjectException;
import java.security.Principal;

@Controller
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/auth/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "/auth/register";
    }

    @GetMapping("/auth/register?error")
    public String registerError(Model model) {
        boolean error = true;

        model.addAttribute("user", new User());
        return "auth/register";
    }

    @GetMapping("/auth/register?username_taken")
    public String registerUsernameTaken(Model model) {
        boolean usernameTaken = true;
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute User user, Model model) {
        try {
            usersService.create(user);
        } catch (InvalidObjectException e) {
            model.addAttribute("error", true);
            return "redirect:/auth/register";
        } catch (javax.persistence.PersistenceException  ex) {
            if(ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                model.addAttribute("usernameTaken", true);
                return "redirect:/auth/register";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/users/{username}")
    public String profile(@PathVariable String username, Model model, Principal principal) {
        User profile = usersService.getUserByUsername(username);
        String title = profile.getUsername();

        if (principal != null) {
            User loggedInUser = usersService.getUserByUsername(principal.getName());
            if (loggedInUser.getId() == profile.getId()) {
                title = "My profile";
            }
        }

        model.addAttribute("title", title);
        model.addAttribute("profile", profile);


        return "users/profile";
    }
}
