package com.restaurantapp.restapp.repository;

import com.restaurantapp.restapp.model.entity.Comment;
import com.restaurantapp.restapp.repository.baserepo.BaseRepository;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {
    List<Comment> findAllByBranchId(long branch_id);
}
