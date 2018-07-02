package com.example.demoPolls.Services;

import com.example.demoPolls.Entities.Answer;
import com.example.demoPolls.Repositories.base.GenericRepository;
import com.example.demoPolls.Services.base.AnswerService;
import com.example.demoPolls.Validators.base.Validator;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;

//Implementation of service
@Service
public class AnswerServiceImpl implements AnswerService {
    private final GenericRepository<Answer> answerRepository;

    //Dependency injection
    public AnswerServiceImpl(GenericRepository<Answer> answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> getAllAnswersToPoll() {
        return answerRepository.getAll();
    }

    @Override
    public void createAnswer(Answer answer) {
        answerRepository.create(answer);
    }

    @Override
    public Answer getAnswerById(int id) {
        return answerRepository.getById(id);
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerRepository.update(answer);
    }
}
