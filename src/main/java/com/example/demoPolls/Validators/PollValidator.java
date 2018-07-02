package com.example.demoPolls.Validators;

import com.example.demoPolls.Entities.Answer;
import com.example.demoPolls.Entities.Poll;
import com.example.demoPolls.Validators.base.Validator;

import java.util.Set;

public class PollValidator implements Validator<Poll> {

    private final int MIN_AMOUNT_OF_ANSWERS = 2;
    private final int MAX_AMOUNT_OF_ANSWERS = 20;

    @Override
    public boolean isValid(Poll object) {
        for (Answer answer:
             object.getAnswerList()) {
            if(!isAnswerValid(answer)) {
                return false;
            }

        }
        return isNameValid(object.getName()) &&
                isAnswerListValid(object.getAnswerList());
    }

    private boolean isNameValid(String name) {
        return name != null;
    }

    private boolean isAnswerListValid(Set<Answer> answerList) {
        return answerList != null &&
                answerList.size() >= MIN_AMOUNT_OF_ANSWERS &&
                answerList.size() <= MAX_AMOUNT_OF_ANSWERS;
    }

    private boolean isAnswerValid(Answer answer) {
        return !answer.getName().equals("");
    }

}
