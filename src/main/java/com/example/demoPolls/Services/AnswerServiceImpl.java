package com.example.demoPolls.Services;

import com.example.demoPolls.Entities.Answer;
import com.example.demoPolls.Repositories.base.GenericRepository;
import com.example.demoPolls.Services.base.AnswerService;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;

//Implementation of service
@Service
public class AnswerServiceImpl implements AnswerService {
    private final GenericRepository<Answer> answerRepository;

    //sloji validator
    //Dependency injection
    public AnswerServiceImpl(GenericRepository<Answer> answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> getAllAnswersToPoll() {
        return answerRepository.getAll();
    }

    @Override
    public void createAnswer(Answer answer) throws InvalidObjectException {
        answerRepository.create(answer);
    }

    @Override
    public Answer getAnswerById(int id) {
        return answerRepository.getById(id);
    }

    @Override
    public void updateAnswer(Answer answer) throws InvalidObjectException {
        answerRepository.update(answer);
    }
}
