package com.team.mybook.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
//@JsonIgnoreProperties({"user"})
public class Statistic {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long statisticID;

    private String status;
    private int score;
    private String type;
    private int time;
    private int currentPage;

    @ManyToOne
    @JoinColumn(name="userID")
    private User user;

    @ManyToOne
    @JoinColumn(name="bookID")
    private Book book;

    public Statistic() {
    }

    public Statistic(String status, int score, String type, int time, int currentPage, User user, Book book) {
        this.status = status;
        this.score = score;
        this.type = type;
        this.time = time;
        this.currentPage = currentPage;
        this.user = user;
        this.book = book;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getStatisticID() {
        return statisticID;
    }

    public void setStatisticID(long statisticID) {
        this.statisticID = statisticID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
