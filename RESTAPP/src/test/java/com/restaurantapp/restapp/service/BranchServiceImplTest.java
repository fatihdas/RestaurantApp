package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;
import com.restaurantapp.restapp.repository.BranchRepository;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceImplTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchServiceImpl branchServiceImpl;

    @Test
    public void save() {

        Branch branch = this.generateBranch();

        Mockito.when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(branch);

        BranchDto createBranch = branchServiceImpl.createBranch(new CreateBranchRequest());

        Assertions.assertEquals(branch, createBranch);
    }

    @Test
    public void getAll() {

        List<Branch> branchList = new ArrayList<>();
        branchList.add(this.generateBranch());

        Mockito.when(branchRepository.findAll()).thenReturn(branchList);

        List<BranchDto> createBranchList = branchServiceImpl.getAllBranches();

        Assertions.assertEquals(branchList, createBranchList);
    }

    @Test
    public void getById() {

        Branch branch = this.generateBranch();

        Mockito.when(branchRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(branch));

        BranchDto createBranch = branchServiceImpl.getBranch(2);

        Assertions.assertEquals(branch, createBranch);
    }

    @Test
    public void update() {

        Branch branch = this.generateBranch();

        Mockito.when(branchRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(branch));

        Mockito.when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(branch);

        BranchDto createBranch = branchServiceImpl.updateBranch(new UpdateBranchRequest(),4);

        Assertions.assertEquals(branch, createBranch);
    }

    @Test
    public void delete() {

        branchRepository.deleteById(2L);
        Mockito.verify(branchServiceImpl).deleteBranch(2L);
    }

    @Test
    public void getWaitingBranchList() {

        List<Branch> branchList = new ArrayList<>();
        branchList.add(this.generateBranch());

        Mockito.when(branchRepository.findBranchesByStatus(Mockito.any())).thenReturn(branchList);

        List<BranchDto> createBranchList = branchServiceImpl.getWaitingBranches("waiting");

        Assertions.assertEquals(branchList, createBranchList);
    }

    private Branch generateBranch() {
        return Branch.builder()
                .name("etilerşubesi")
                .menu(Menu.builder().build())
                .build();
    }

}