package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.enumerated.Status;
import com.restaurantapp.restapp.exception.BranchNotFoundException;
import com.restaurantapp.restapp.model.Branch;
import com.restaurantapp.restapp.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public Branch save(Branch branch) {

        return branchRepository.save(branch);
    }

    public List<Branch> getAll() {

        return branchRepository.findAll();
    }

    public Branch getById(long id) {

        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));
    }

    public Branch update(Branch branch) {

        branchRepository.findById(branch.getId()).orElseThrow(() -> new BranchNotFoundException(branch.getId()));
        return branchRepository.save(branch);


    }

    public Branch delete(long id) {

        branchRepository.deleteById(id);

        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));
    }

    public List<Branch> getWaitingBranchList(){

        return branchRepository.findAllByStatus(Status.WAITING);
    }
}
