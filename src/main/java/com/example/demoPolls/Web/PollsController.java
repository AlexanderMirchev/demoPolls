package com.example.demoPolls.Web;

import com.example.demoPolls.Entities.Answer;
import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Entities.SupportEntities.AnswerForm;
import com.example.demoPolls.Entities.User;
import com.example.demoPolls.Services.base.AnswerService;
import com.example.demoPolls.Services.base.PollService;
import com.example.demoPolls.Services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InvalidObjectException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/polls")
public class PollsController {
    @Autowired
    private PollService pollService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/createPoll")
    public String createPoll(Model model) {
        Poll poll = new Poll();
        model.addAttribute("poll", poll);
        AnswerForm answers = new AnswerForm();
        model.addAttribute("answers", answers);
        return "polls/createPoll";
    }

    @PostMapping("/createPoll")
    public String createPoll(
        @ModelAttribute Poll poll,
        @ModelAttribute AnswerForm answers,
        Principal principal
    ) {
        try {
            User user = usersService.getUserByUsername(principal.getName());
            poll.setUser(user);
            List<Answer> answerList = new ArrayList<>();
            answers.getAnswers()
                    .forEach(answer -> answerList.add(new Answer(answer)));
            answerList.forEach(answer -> answer.setPoll(poll));
            poll.setAnswerList(answerList);
            pollService.createPoll(poll);
            return "redirect:/";
        } catch (InvalidObjectException e) {
            return "redirect:/polls/createPoll";
        }
    }
}
