package com.example.demoPolls.Web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController implements ErrorController {

    private static final String PATH = "/error";

    @GetMapping("/error")
    public String error() {
        return "error.html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}