package com.example.demoPolls.Web;

import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Services.base.PollService;
import com.example.demoPolls.Services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {

    private final PollService pollService;

    @Autowired
    public HomeController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        List<Poll> polls = pollService.getAllPollsNew();
        model.addAttribute("polls", polls);
        return "homepage";
    }

    @GetMapping("/top")
    public String homepageTop(Model model) {
        List<Poll> polls = pollService.getAllPollsTop();
        model.addAttribute("polls", polls);
        return "homepageTop";
    }
}
