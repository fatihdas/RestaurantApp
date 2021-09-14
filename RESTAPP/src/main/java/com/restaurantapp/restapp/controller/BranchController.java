package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.get.BranchPageGetRequest;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    private final BranchServiceImpl branchServiceImpl;

    public BranchController(BranchServiceImpl branchServiceImpl) {
        this.branchServiceImpl = branchServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@Valid @RequestBody CreateBranchRequest request) {

        return new ResponseEntity<>(branchServiceImpl.createBranch(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> getNearBranches(@RequestBody BranchPageGetRequest branchPageGetRequest) {

        return new ResponseEntity<>(branchServiceImpl.getBranchesByCounty(branchPageGetRequest), HttpStatus.OK);
    }

    @GetMapping("/status/{value}")
    public ResponseEntity<List<BranchDto>> getByStatus(@PathVariable("value") String value) {

        return new ResponseEntity<>(branchServiceImpl.getBranchesByStatus(value), HttpStatus.OK);
    }

    @PutMapping("{branchId}/{status}")
    public ResponseEntity<BranchDto> updateStatus(@PathVariable long branchId, @PathVariable String status) {
        return new ResponseEntity<>(branchServiceImpl.changeBranchStatus(branchId, status), HttpStatus.OK);
    }

}
