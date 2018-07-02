package com.example.demoPolls.Services.base;

import com.example.demoPolls.Entities.Poll;

import java.io.InvalidObjectException;
import java.util.List;

//Interface for service with polls
public interface PollService {
    List<Poll> getAllPolls();

    Poll getPollById(int id);

    List<Poll> getAllPollsNew();

    List<Poll> getAllPollsTop();

    void createPoll(Poll poll) throws InvalidObjectException;

    void updatePoll(Poll poll) throws InvalidObjectException;
}
