package com.team.mybook.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long commentID;
    private String content;

    @ManyToOne
    @JoinColumn(name="bookID")
    private Book book;

    public Comment() {

    }

    public Comment(String content, Book book) {
        this.content = content;
        this.book = book;
    }

    @JsonIgnore
    public Book getBook() {
        return book;
    }

    @JsonProperty
    public void setBook(Book book) {
        this.book = book;
    }

    public long getCommentID() {
        return commentID;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
