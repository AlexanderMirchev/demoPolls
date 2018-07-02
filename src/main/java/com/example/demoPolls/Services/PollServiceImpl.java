package com.example.demoPolls.Services;

import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Repositories.base.GenericRepository;
import com.example.demoPolls.Services.base.PollService;
import com.example.demoPolls.Validators.base.Validator;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {
    private final GenericRepository<Poll> pollRepository;
    private final Validator<Poll> pollValidator;

    public PollServiceImpl(GenericRepository<Poll> pollRepository, Validator<Poll> pollValidator) {
        this.pollRepository = pollRepository;
        this.pollValidator = pollValidator;
    }

    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.getAll();
    }

    @Override
    public List<Poll> getAllPollsNew() {
        List<Poll> allPolls = getAllPolls();
        Collections.reverse(allPolls);
        return allPolls;
    }

    @Override
    public List<Poll> getAllPollsTop() {
        List<Poll> allPolls = getAllPolls();
        Collections.sort(allPolls,
                Comparator.comparingInt(Poll::getNumberOfVotes).reversed());
        return allPolls;
    }
    @Override
    public Poll getPollById(int id) {
        return pollRepository.getById(id);
    }



    @Override
    public void createPoll(Poll poll) throws InvalidObjectException {
        if(!pollValidator.isValid(poll)) {
            throw new InvalidObjectException("Invalid poll");
        }
        pollRepository.create(poll);
    }

    @Override
    public void updatePoll(Poll poll) {
        pollRepository.update(poll);
    }

}
