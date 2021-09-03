package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.CommentDto;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.service.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(CreateCommentRequest request) {
        return new ResponseEntity<>(commentService.createComment(request), HttpStatus.OK);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<List<CommentDto>> getAllCommentByBranchId(@PathVariable long branchId) {
        return new ResponseEntity<>(commentService.getAllComments(branchId), HttpStatus.OK);
    }
}
