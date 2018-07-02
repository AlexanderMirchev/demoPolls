package com.example.demoPolls.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//Main entity
@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String name;
    private int numberOfVotes;

    //Every poll must have a creator
    //Many polls one user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID ")
    private User user;

    //One to many mapping with answers
    @OneToMany(mappedBy = "poll", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable (
            name = "USERS_POLLS",
            joinColumns = @JoinColumn(name = "POLL_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID",referencedColumnName = "id")
    )
    private Set<User> usersList;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Set<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(Set<User> usersList) {
        this.usersList = usersList;
    }
}
