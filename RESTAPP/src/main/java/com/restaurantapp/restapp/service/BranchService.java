package com.restaurantapp.restapp.service;

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

    public Branch save(Branch branch){

        return branchRepository.save(branch);
    }

    public List<Branch> getAll(){

        return branchRepository.findAll();
    }

    public Branch getById(long id){

        return branchRepository.findById(id).orElse(null);
    }

    public Branch update(Branch branch,long id){

        Branch branch1 = branchRepository.findById(id).orElse(null);

        branch1.setId(branch.getId());
        branch1.setAddress(branch.getAddress());
        branch1.setComment(branch.getComment());
        branch1.setMenu(branch.getMenu());
        branch1.setName(branch.getName());
        branch1.setRestaurant(branch.getRestaurant());
        branch1.setStatus(branch.getStatus());

        branchRepository.save(branch1);

        return branch1;

    }

    public Branch delete(long id){

        branchRepository.deleteById(id);

        return branchRepository.findById(id).orElse(null);
    }
}
