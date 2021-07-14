package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
