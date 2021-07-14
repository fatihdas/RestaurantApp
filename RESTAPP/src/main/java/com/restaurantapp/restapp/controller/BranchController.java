package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Basket;
import com.restaurantapp.restapp.entity.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<Branch> add(@RequestBody Branch branch){

        return new ResponseEntity(branchService.save(basket), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAll(){

        return new ResponseEntity(branchService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Branch> getById(@PathVariable long id){

        return new ResponseEntity(branchService.getById(id),HttpStatus.OK)
    }

    @PutMapping
    public ResponseEntity<Branch> update(@RequestBody Branch branch){

        return new ResponseEntity(branchService.update(basket),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Branch> delete(@PathVariable long id){
        return new ResponseEntity(branchService.delete(id),HttpStatus.OK);
    }
}
