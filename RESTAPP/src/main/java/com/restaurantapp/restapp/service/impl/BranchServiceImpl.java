package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.BranchNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import com.restaurantapp.restapp.model.entity.enumerated.StatusEnumConverter;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
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

        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException(id));
        return branchEntityToDtoConverter.convert(branch);
    }

    @Override
    public List<BranchDto> getNearBranches(long countyId) {

        List<Branch> branchList = branchRepository.findBranchesByAddress_CountyId(countyId);
        return branchList.stream().map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
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

        if ()
        BranchStatus status = statusEnumConverter.convertToDatabaseColumn(value);
        List<Branch> branchList = branchRepository.findBranchesByBranchStatus(status);
        return branchList.stream().map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }


    public BranchDto changeBranchStatus(long branchId, String value) throws Exception {
        if (!(httpServletRequest.isUserInRole(UserRoles.ADMIN.toString()))) {
            throw new Exception("Role is not valid!");
        }
        branchRepository.findById(branchId).orElseThrow(() -> new BranchNotFoundException(branchId))
                .setBranchStatus(statusEnumConverter.convertToDatabaseColumn(value));
        return branchEntityToDtoConverter.convert(branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException(branchId)));
    }

    public Branch getBranchByid(long id) {
        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException());
    }
}
