package com.team.mybook.controller;

import com.team.mybook.data.entity.Comment;
import com.team.mybook.data.entity.Statistic;
import com.team.mybook.data.repository.CommentRepository;
import com.team.mybook.data.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/api/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public void addNewComment (@RequestBody Comment requestComment){
        Comment comment = new Comment(requestComment.getContent(), requestComment.getBook());
        commentRepository.save(comment);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Comment> getAllComments(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return commentRepository.findAll();
    }
}
