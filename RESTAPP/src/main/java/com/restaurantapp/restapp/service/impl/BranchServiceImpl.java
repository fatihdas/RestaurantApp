package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.BranchNotFoundException;
import com.restaurantapp.restapp.exception.InvalidOwnerException;
import com.restaurantapp.restapp.exception.InvalidRoleException;
import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import com.restaurantapp.restapp.model.entity.enumerated.StatusEnumConverter;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.get.BranchPageGetRequest;
import com.restaurantapp.restapp.repository.BranchRepository;
import com.restaurantapp.restapp.service.BranchService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchEntityToDtoConverter branchEntityToDtoConverter;
    private final CreateBranchRequestConverter createBranchRequestConverter;
    private final StatusEnumConverter statusEnumConverter;
    private final UserServiceImpl userService;

    public BranchServiceImpl(BranchRepository branchRepository,
                             BranchEntityToDtoConverter branchEntityToDtoConverter,
                             CreateBranchRequestConverter createBranchRequestConverter,
                             StatusEnumConverter statusEnumConverter,
                             UserServiceImpl userService) {
        this.branchRepository = branchRepository;
        this.branchEntityToDtoConverter = branchEntityToDtoConverter;
        this.createBranchRequestConverter = createBranchRequestConverter;
        this.statusEnumConverter = statusEnumConverter;
        this.userService = userService;
    }

    @Transactional
    public BranchDto createBranch(CreateBranchRequest request) {

        long branchOwnerId = branchRepository.getOwnerIdByRestaurantId(request.getRestaurantId());
        if (userService.isOwner(branchOwnerId)) {
            Branch branch = branchRepository.save(createBranchRequestConverter.convert(request));
            return branchEntityToDtoConverter.convert(branch);
        } else {
            throw new InvalidOwnerException();
        }

    }

    @Override
    public BranchDto getBranchDto(long id) {

        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException());
        return branchEntityToDtoConverter.convert(branch);
    }

    @Override
    public List<BranchDto> getBranchesByCounty(BranchPageGetRequest branchPageGetRequest) {
        Pageable paging = PageRequest.of(branchPageGetRequest.getPageNo(), branchPageGetRequest.getPageSize(), Sort.by(branchPageGetRequest.getSortBy()));
        if (userService.hasRole(UserRoles.BUYER)) {
            Page<Branch> branchList = branchRepository.findBranchesByAddress_CountyId(branchPageGetRequest.getCountyId(), paging);
            return branchList.stream().map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
        } else {
            throw new InvalidRoleException("role is not buyer!");
        }

    }

    @Override
    public List<BranchDto> getBranchesByStatus(String value) {

        if (userService.hasRole(UserRoles.ADMIN)) {
            BranchStatus status = statusEnumConverter.convertToDatabaseColumn(value);
            List<Branch> branchList = branchRepository.findBranchesByBranchStatus(status);
            return branchList.stream().map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
        } else {
            throw new InvalidRoleException("Role is not admin!");
        }
    }

    @Override
    @Cacheable(value = "branches")
    public List<BranchDto> getAll() {
        List<Branch> branchList = branchRepository.findAll();
        return branchList.stream().map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }


    @Override
    public BranchDto changeBranchStatus(long branchId, String value) {
        if (userService.hasRole(UserRoles.ADMIN)) {
            Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new BranchNotFoundException());
            branch.setBranchStatus(statusEnumConverter.convertToDatabaseColumn(value));
            return branchEntityToDtoConverter.convert(branchRepository.save(branch));
        } else {
            throw new InvalidRoleException("Role is not admin!");
        }

    }

    @Override
    public Branch getBranchByid(long id) {
        return branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException());
    }
}
