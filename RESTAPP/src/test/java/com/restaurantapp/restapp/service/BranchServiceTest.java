package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Branch;
import com.restaurantapp.restapp.model.Menu;
import com.restaurantapp.restapp.model.enumerated.Status;
import com.restaurantapp.restapp.repository.BranchRepository;
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
public class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchService branchService;

    @Test
    public void save() {

        Branch branch = this.generateBranch();

        Mockito.when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(branch);

        Branch createBranch = branchService.save(new Branch());

        Assertions.assertEquals(branch, createBranch);
    }

    @Test
    public void getAll() {

        List<Branch> branchList = new ArrayList<>();
        branchList.add(this.generateBranch());

        Mockito.when(branchRepository.findAll()).thenReturn(branchList);

        List<Branch> createBranchList = branchService.getAll();

        Assertions.assertEquals(branchList, createBranchList);
    }

    @Test
    public void getById() {

        Branch branch = this.generateBranch();

        Mockito.when(branchRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(branch));

        Branch createBranch = branchService.getById(2);

        Assertions.assertEquals(branch, createBranch);
    }

    @Test
    public void update() {

        Branch branch = this.generateBranch();

        Mockito.when(branchRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(branch));

        Mockito.when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(branch);

        Branch createBranch = branchService.update(new Branch());

        Assertions.assertEquals(branch, createBranch);
    }

    @Test
    public void delete() {

        branchRepository.deleteById(2L);
        Mockito.verify(branchRepository).deleteById(2L);

        String deleteBranch = branchService.delete(3);

        Assertions.assertEquals("SUCCESS", deleteBranch);
    }

    @Test
    public void getWaitingBranchList(){

        List<Branch> branchList = new ArrayList<>();
        branchList.add(this.generateBranch());

        Mockito.when(branchRepository.findAllByStatus(Mockito.any())).thenReturn(branchList);

        List<Branch> createBranchList = branchService.getWaitingBranchList();

        Assertions.assertEquals(branchList, createBranchList);
    }

    private Branch generateBranch() {
        return Branch.builder()
                .name("etilerşubesi")
                .menu(Menu.builder().build())
                .build();
    }

}