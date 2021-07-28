package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.Comment;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends BaseRepository<Comment> {
}
