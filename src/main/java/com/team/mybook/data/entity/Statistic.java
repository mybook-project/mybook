package com.team.mybook.data.entity;

import javax.persistence.*;

@Entity
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

    public User getUser() {
        return user;
    }

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
