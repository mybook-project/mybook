package com.team.mybook.data.repository;

import com.team.mybook.data.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
