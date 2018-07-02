package com.example.demoPolls.Entities.SupportEntities;

import org.springframework.util.AutoPopulatingList;

import java.util.List;

//This is an entity that is used when a poll is created.
//Its purpose is to store a list of strings that are later set as names of answers to a certain poll.
public class AnswerForm {
    //AutoPopulatingList rather than ArrayList
    private List<String> answers = new AutoPopulatingList<>(String.class);


    public AnswerForm(){


    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

}
