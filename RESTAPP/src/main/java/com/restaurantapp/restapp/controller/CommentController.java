package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.model.request.update.UpdateCommentRequest;
import com.restaurantapp.restapp.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CreateCommentRequest request) {

        return new ResponseEntity<>(commentServiceImpl.createComment(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll() {

        return new ResponseEntity<>(commentServiceImpl.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable long id) {

        return new ResponseEntity<>(commentServiceImpl.getComment(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentDto> update(@RequestBody UpdateCommentRequest request, long id) {

        return new ResponseEntity<>(commentServiceImpl.updateComment(request, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        commentServiceImpl.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}
