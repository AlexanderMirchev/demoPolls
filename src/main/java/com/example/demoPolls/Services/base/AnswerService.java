package com.example.demoPolls.Services.base;

import com.example.demoPolls.Entities.Answer;

import java.io.InvalidObjectException;
import java.util.List;

//Interface for service with answers
public interface AnswerService {

    List<Answer> getAllAnswersToPoll();

    void createAnswer(Answer answer) throws InvalidObjectException;

    Answer getAnswerById(int id);

    void updateAnswer(Answer answer) throws InvalidObjectException;

}
