package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import com.restaurantapp.restapp.service.impl.MenuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("branch")
public class BranchController {

    private final BranchServiceImpl branchServiceImpl;
    private final MenuServiceImpl menuService;

    public BranchController(BranchServiceImpl branchServiceImpl, MenuServiceImpl menuService) {
        this.branchServiceImpl = branchServiceImpl;
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@Valid @RequestBody CreateBranchRequest request) {

        return new ResponseEntity<>(branchServiceImpl.createBranch(request), HttpStatus.CREATED);
    }

    @GetMapping("/{countyid}")
    public ResponseEntity<List<BranchDto>> getNearBranches(@PathVariable long countyid) {

        return new ResponseEntity<>(branchServiceImpl.getNearBranches(countyid), HttpStatus.OK);
    }

    @GetMapping("/status/{value}")
    public ResponseEntity<List<BranchDto>> getByStatus(@PathVariable("value") String value) throws Exception {

        return new ResponseEntity<>(branchServiceImpl.getWaitingBranches(value), HttpStatus.OK);
    }

    @PutMapping("{branchId}/{status}")
    public ResponseEntity<BranchDto> updateStatus(@PathVariable long branchId,@PathVariable String status) throws Exception {
        return new ResponseEntity<>(branchServiceImpl.changeBranchStatus(branchId,status),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable long id) {

        branchServiceImpl.deleteBranch(id);
        return ResponseEntity.ok().build();
    }

}
