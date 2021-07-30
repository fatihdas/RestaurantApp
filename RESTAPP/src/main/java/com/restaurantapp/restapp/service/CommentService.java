package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.CommentNotFoundException;
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

    public Comment save(Comment comment) {

        return commentRepository.save(comment);
    }

    public List<Comment> getAll() {

        return commentRepository.findAll();
    }

    public Comment getById(long id) {

        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Comment update(Comment comment) {

        commentRepository.findById(comment.getId()).orElseThrow(() -> new CommentNotFoundException(comment.getId()));
        return commentRepository.save(comment);
    }

    public String delete(long id) {

        commentRepository.deleteById(id);
        return "SUCCESS";
    }
}
