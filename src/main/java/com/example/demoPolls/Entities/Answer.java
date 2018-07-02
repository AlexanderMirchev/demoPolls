package com.example.demoPolls.Entities;

import javax.persistence.*;

//Each Poll must have Answers
@Entity
public class Answer  {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    public String name;
    private int numberOfVotes;

    //Many answers to a single poll
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POLL_ID")
    private Poll poll;


    //Default Constructor
    public Answer(){
        setNumberOfVotes(0);
    }

    //Constructor used when creating answer with given name
    public Answer(String name) {
        setName(name);
        setNumberOfVotes(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}

