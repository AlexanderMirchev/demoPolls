package com.example.demoPolls.Web;

import com.example.demoPolls.Entities.User;
import com.example.demoPolls.Services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.InvalidObjectException;
import java.security.AuthProvider;
import java.security.Principal;

@Controller
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.GET)
    public String register(Model model,
                           @RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "username_taken",required = false) String username_taken) {
        if(error!= null && error.equals("true")) {
            model.addAttribute("error",true);
        }
        if(username_taken != null && username_taken.equals("true")) {
            model.addAttribute("username_taken",true);
        }
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String register(@ModelAttribute User user, Model model, HttpServletRequest httpServletRequest) {
        try {
            usersService.create(user);
        } catch (InvalidObjectException e) {
            model.addAttribute("error",true);
            return "redirect:/auth/register";
        } catch (javax.persistence.PersistenceException  ex) {
            if(ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                model.addAttribute("username_taken",true);
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
