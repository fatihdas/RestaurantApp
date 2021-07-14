package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Comment;
import com.restaurantapp.restapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment){

        return commentRepository.save(comment);
    }

    public List<Comment> getAll(){

        return commentRepository.findAll();
    }

    public Comment getById(long id){

        return commentRepository.findById(id).orElse(null);
    }

    public Comment update(Comment comment, long id){

        Comment comment1 = commentRepository.findById(id).orElse(null);

        comment1.setId(comment.getId());
        comment1.setContent(comment.getContent());
        comment1.setUser(comment.getUser());
        comment1.setBranch(comment.getBranch());
        comment1.setDate(comment.getDate());

        commentRepository.save(comment1);

        return comment1;
    }

    public Comment delete(long id){

        commentRepository.deleteById(id);

        return commentRepository.findById(id).orElse(null);
    }
}
