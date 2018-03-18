package com.team.mybook.data.entity;

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

    public Book getBook() {
        return book;
    }

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
