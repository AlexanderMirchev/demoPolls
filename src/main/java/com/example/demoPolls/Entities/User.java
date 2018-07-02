package com.example.demoPolls.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//User entity
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(unique = true)
    private String username;
    private String password;

    //Mapping user and polls
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Poll> polls;

    public User(int id, String username, String password, List<Poll> polls) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.polls = polls;
    }

    @ManyToMany(mappedBy = "usersList",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Poll> answeredPolls;


    //Default constructor
    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }

    public Set<Poll> getAnsweredPolls() {
        return answeredPolls;
    }

    public void setAnsweredPolls(Set<Poll> answeredPolls) {
        this.answeredPolls = answeredPolls;
    }
}
