package com.example.demoPolls.Web;

import com.example.demoPolls.Entities.Answer;
import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Entities.User;
import com.example.demoPolls.Services.base.AnswerService;
import com.example.demoPolls.Services.base.PollService;
import com.example.demoPolls.Services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.InvalidObjectException;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/polls/voting")
public class VoteController {
    private final PollService pollService;
    private final AnswerService answerService;
    private UsersService usersService;

    @Autowired
    public VoteController(PollService pollService, AnswerService answerService, UsersService usersService) {
        this.pollService = pollService;
        this.answerService = answerService;
        this.usersService = usersService;
    }

    @GetMapping("/vote/{id}")
    public String vote(@PathVariable String id, Model model, Principal principal) {

        User user = usersService.getUserByUsername(principal.getName());
        Poll poll = pollService.getPollById(Integer.parseInt(id));
        model.addAttribute("poll", poll);
        List<String> userListNames = new ArrayList<>();
        poll.getUsersList().forEach(user1 -> userListNames.add(user1.getUsername()));
        if(userListNames.contains(user.getUsername())) {
            model.addAttribute("hasVoted", true);
            return "polls/voting/results";
        }
        if(user.getId() == poll.getUser().getId()) {
            model.addAttribute("isCreator", true);
            return "polls/voting/results";
        }

        return "polls/voting/vote";
    }

    @GetMapping("/results/answer/{answer_id}")
    public String results(@PathVariable String answer_id,
                          Model model, Principal principal) throws InvalidObjectException {
        Answer answer = answerService.getAnswerById(Integer.parseInt(answer_id));
        User user = usersService.getUserByUsername(principal.getName());
        Poll poll = answer.getPoll();

        int numberOfVotes = answer.getNumberOfVotes();
        numberOfVotes++;
        answer.setNumberOfVotes(numberOfVotes);
        answerService.updateAnswer(answer);

        Set<User> usersList = poll.getUsersList();
        usersList.add(user);
        poll.setUsersList(usersList);

        numberOfVotes = poll.getNumberOfVotes();
        numberOfVotes++;
        poll.setNumberOfVotes(numberOfVotes);
        pollService.updatePoll(poll);

        model.addAttribute("poll", poll);
        return "polls/voting/results";
    }

    @GetMapping("results/{poll_id}")
    public String resultsOnly(@PathVariable String poll_id,
                              Model model) {
        Poll poll = pollService.getPollById(Integer.parseInt(poll_id));
        model.addAttribute("poll", poll);
        return "polls/voting/results";

    }
}
