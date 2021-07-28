package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Comment;
import com.restaurantapp.restapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> add(@RequestBody Comment comment) {

        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAll() {

        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getById(@PathVariable long id) {

        return new ResponseEntity<>(commentService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Comment> update(@RequestBody Comment comment) {

        return new ResponseEntity<>(commentService.update(comment), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Comment> delete(@PathVariable long id) {
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.OK);
    }
}
