package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.BranchNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.MealDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Meal;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import com.restaurantapp.restapp.model.entity.enumerated.StatusEnumConverter;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.create.CreateMealRequest;
import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;
import com.restaurantapp.restapp.repository.BranchRepository;
import com.restaurantapp.restapp.service.BranchService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchEntityToDtoConverter branchEntityToDtoConverter;
    private final CreateBranchRequestConverter createBranchRequestConverter;
    private final StatusEnumConverter statusEnumConverter;
    private final HttpServletRequest httpServletRequest;

    public BranchServiceImpl(BranchRepository branchRepository,
                             BranchEntityToDtoConverter branchEntityToDtoConverter,
                             CreateBranchRequestConverter createBranchRequestConverter,
                             StatusEnumConverter statusEnumConverter, HttpServletRequest httpServletRequest) {
        this.branchRepository = branchRepository;
        this.branchEntityToDtoConverter = branchEntityToDtoConverter;
        this.createBranchRequestConverter = createBranchRequestConverter;
        this.statusEnumConverter = statusEnumConverter;
        this.httpServletRequest = httpServletRequest;
    }

    public BranchDto createBranch(CreateBranchRequest request) {

        return branchEntityToDtoConverter.convert(branchRepository.save(createBranchRequestConverter.convert(request)));
    }

    public List<BranchDto> getAllBranches() {

        return branchRepository.findAll().stream().map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public BranchDto getBranchDto(long id) {

        return branchEntityToDtoConverter.convert(branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException(id)));
    }

    @Override
    public List<BranchDto> getNearBranches(String countyName) {

        return branchRepository.findBranchesByAddress_CountyNameContainingIgnoreCase(countyName).stream()
                .map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public String updateBranch(UpdateBranchRequest request, long id) {

        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException());

        branch.setName(request.getName());

        return "Success branch has been updated! id:" + branch.getId();


    }

    public void deleteBranch(long id) {

        branchRepository.deleteById(id);
    }

    public List<BranchDto> getWaitingBranches(String value) throws Exception {

        if (!(httpServletRequest.isUserInRole(Roles.ADMIN.toString()))) {
            throw new Exception("Role is not valid!");
        }

        return branchRepository.findBranchesByStatus(statusEnumConverter.convertToDatabaseColumn(value)).stream()
                .map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }


    public BranchDto changeBranchStatus(long branchId, String value) throws Exception {
        if (!(httpServletRequest.isUserInRole(Roles.ADMIN.toString()))) {
            throw new Exception("Role is not valid!");
        }
        branchRepository.findById(branchId).orElseThrow(() -> new BranchNotFoundException(branchId))
                .setStatus(statusEnumConverter.convertToDatabaseColumn(value));
        return branchEntityToDtoConverter.convert(branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException(branchId)));
    }

    public Branch getBranchByid(long id) {
        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException());
    }
}
