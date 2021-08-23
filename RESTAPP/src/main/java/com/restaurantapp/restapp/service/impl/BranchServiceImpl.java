package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.BranchNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.toentity.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.update.toentity.UpdateBranchRequestConverter;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.enumerated.StatusEnumConverter;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;
import com.restaurantapp.restapp.repository.BranchRepository;
import com.restaurantapp.restapp.service.BranchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchEntityToDtoConverter branchEntityToDtoConverter;
    private final CreateBranchRequestConverter createBranchRequestConverter;
    private final UpdateBranchRequestConverter updateBranchRequestConverter;
    private final StatusEnumConverter statusEnumConverter;

    public BranchServiceImpl(BranchRepository branchRepository,
                             BranchEntityToDtoConverter branchEntityToDtoConverter,
                             CreateBranchRequestConverter createBranchRequestConverter,
                             UpdateBranchRequestConverter updateBranchRequestConverter,
                             StatusEnumConverter statusEnumConverter) {
        this.branchRepository = branchRepository;
        this.branchEntityToDtoConverter = branchEntityToDtoConverter;
        this.createBranchRequestConverter = createBranchRequestConverter;
        this.updateBranchRequestConverter = updateBranchRequestConverter;
        this.statusEnumConverter = statusEnumConverter;
    }

    public BranchDto createBranch(CreateBranchRequest request) {

        return branchEntityToDtoConverter.convert(branchRepository.save(createBranchRequestConverter.convert(request)));
    }

    public List<BranchDto> getAllBranches() {

        return branchRepository.findAll().stream().map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public BranchDto getBranch(long id) {

        return branchEntityToDtoConverter.convert(branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException(id)));
    }

    @Override
    public List<BranchDto> getNearBranches(String countyName) {

//        County county = countyDtoToEntityConverter.convert(countyDto);

        return branchRepository.findBranchesByAddress_CountyNameContainingIgnoreCase(countyName).stream()
                .map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public BranchDto updateBranch(UpdateBranchRequest request, long id) {

        Branch branch = branchRepository.findById(id).orElseThrow(() -> new BranchNotFoundException());
        Branch updatedFields = updateBranchRequestConverter.convert(request);

        branch.setAddress(updatedFields.getAddress());
        branch.setMenu(updatedFields.getMenu());
        branch.setName(updatedFields.getName());

        return branchEntityToDtoConverter.convert(branchRepository.save(branch));


    }

    public void deleteBranch(long id) {

        branchRepository.deleteById(id);
    }

    public List<BranchDto> getWaitingBranches(String value) {

        return branchRepository.findBranchesByStatus(statusEnumConverter.convertToDatabaseColumn(value)).stream()
                .map(branchEntityToDtoConverter::convert).collect(Collectors.toList());
    }
}
