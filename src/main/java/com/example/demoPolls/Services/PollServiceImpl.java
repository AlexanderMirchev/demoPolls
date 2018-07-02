package com.example.demoPolls.Services;

import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Repositories.base.GenericRepository;
import com.example.demoPolls.Services.base.PollService;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {
    private final GenericRepository<Poll> pollRepository;
//    private final Validator<Poll> productValidator;

    public PollServiceImpl(GenericRepository<Poll> pollRepository /*, Validator<Product> productValidator*/) {
        this.pollRepository = pollRepository;
//        this.productValidator = productValidator;
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
        //sloji validator nqkoga
        pollRepository.create(poll);
    }

    @Override
    public void updatePoll(Poll poll) throws InvalidObjectException {
        pollRepository.update(poll);
    }

}
