package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Branch;
import com.restaurantapp.restapp.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<Branch> add(@RequestBody Branch branch) {

        return new ResponseEntity<>(branchService.save(branch), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAll() {

        return new ResponseEntity<>(branchService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Branch> getById(@PathVariable long id) {

        return new ResponseEntity<>(branchService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/waiting")
    public ResponseEntity<List<Branch>> getAllBtWaiting(){

        return new ResponseEntity<>(branchService.getWaitingBranchList(),HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Branch> update(@RequestBody Branch branch) {

        return new ResponseEntity<>(branchService.update(branch), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {

        return new ResponseEntity(branchService.delete(id), HttpStatus.OK);
    }
}
