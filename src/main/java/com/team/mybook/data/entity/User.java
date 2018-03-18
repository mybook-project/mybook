package com.team.mybook.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long userID;

    private String name;
    private String password;
    private String email;
    private String gender;
    private int age;

    @OneToMany(cascade=CascadeType.ALL , mappedBy = "user")
    private List<Statistic> statistics;

    public List<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}