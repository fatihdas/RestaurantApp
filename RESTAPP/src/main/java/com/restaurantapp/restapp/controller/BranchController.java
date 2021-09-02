package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.create.CreateCommentRequest;
import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;
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

    @GetMapping
    public ResponseEntity<List<BranchDto>> getAllBranches() {

        return new ResponseEntity<>(branchServiceImpl.getAllBranches(), HttpStatus.OK);
    }

    @GetMapping("/branches/{countyName}")
    public ResponseEntity<List<BranchDto>> getNearBranches(@PathVariable String countyName) {

        return new ResponseEntity<>(branchServiceImpl.getNearBranches(countyName), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getById(@PathVariable long id) {

        return new ResponseEntity<>(branchServiceImpl.getBranchDto(id), HttpStatus.OK);
    }

    @GetMapping("/status/{value}")
    public ResponseEntity<List<BranchDto>> getAllWaiting(@PathVariable("value") String value) throws Exception {

        return new ResponseEntity<>(branchServiceImpl.getWaitingBranches(value), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBranch(@Valid @RequestBody UpdateBranchRequest request, @PathVariable long id) {

        return new ResponseEntity<>(branchServiceImpl.updateBranch(request, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable long id) {

        branchServiceImpl.deleteBranch(id);
        return ResponseEntity.ok().build();
    }

}
