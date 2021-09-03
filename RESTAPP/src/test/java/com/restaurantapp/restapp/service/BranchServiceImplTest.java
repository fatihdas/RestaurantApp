//package com.restaurantapp.restapp.service;
//
//import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
//import com.restaurantapp.restapp.model.dto.AddressDto;
//import com.restaurantapp.restapp.model.dto.BranchDto;
//import com.restaurantapp.restapp.model.dto.MenuDto;
//import com.restaurantapp.restapp.model.entity.Address;
//import com.restaurantapp.restapp.model.entity.Branch;
//import com.restaurantapp.restapp.model.entity.Menu;
//import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
//import com.restaurantapp.restapp.model.entity.enumerated.StatusEnumConverter;
//import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
//import com.restaurantapp.restapp.model.request.update.UpdateBranchRequest;
//import com.restaurantapp.restapp.repository.BranchRepository;
//import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(MockitoJUnitRunner.class)
//public class BranchServiceImplTest {
//
//    @Mock
//    private BranchEntityToDtoConverter branchEntityToDtoConverter;
//
//    @Mock
//    private StatusEnumConverter statusEnumConverter;
//
//    @Mock
//    private CreateBranchRequestConverter createBranchRequestConverter;
//
//    @Mock
//    private BranchRepository branchRepository;
//
//    @Spy
//    @InjectMocks
//    private BranchServiceImpl branchServiceImpl;
//
//    @Test
//    public void save() {
//
//        BranchDto branch = this.generateBranchDto();
//        CreateBranchRequest request = CreateBranchRequest.builder().id(22).build();
//
//        Mockito.when(createBranchRequestConverter.convert(Mockito.any(CreateBranchRequest.class)))
//                .thenReturn(new Branch());
//        Mockito.when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(new Branch());
//        Mockito.when(branchEntityToDtoConverter.convert(Mockito.any(Branch.class))).thenReturn(branch);
//
//        BranchDto createBranch = branchServiceImpl.createBranch(request);
//
//        Assertions.assertEquals(request.getId(), createBranch.getId());
//    }
//
//    @Test
//    public void getById() {
//
//        BranchDto branch = this.generateBranchDto();
//
//        Mockito.when(branchRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(new Branch()));
//        Mockito.when(branchEntityToDtoConverter.convert(Mockito.any(Branch.class)))
//                .thenReturn(branch);
//
//        BranchDto createBranch = branchServiceImpl.getBranchDto(2);
//
//        Assertions.assertEquals(branch, createBranch);
//    }
//
//    @Test
//    public void update() {
//
//        Branch branch = this.generateBranch();
//
//        String message = "Success branch has been updated! id:" + branch.getId();
//
//        Mockito.when(branchRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(branch));
//
//        String createBranch = branchServiceImpl.updateBranch(new UpdateBranchRequest(),22);
//
//        Assertions.assertEquals(message, createBranch);
//    }
//
//    @Test
//    public void getWaitingBranchList() throws Exception {
//
//        List<Branch> branchList = new ArrayList<>();
//        branchList.add(this.generateBranch());
//
//        Mockito.when(branchRepository.findBranchesByBranchStatus(Mockito.any())).thenReturn(branchList);
//        Mockito.when(branchEntityToDtoConverter.convert(Mockito.any(Branch.class))).thenReturn(this.generateBranchDto());
//
//        BranchDto createBranchList = branchServiceImpl.getWaitingBranches("waiting").get(0);
//
//        Assertions.assertEquals(branchList.get(0).getId(), createBranchList.getId());
//    }
//
//    private BranchDto generateBranchDto() {
//        return BranchDto.builder()
//                .name("etilerşubesi")
//                .menuDto(MenuDto.builder().build())
//                .addressDto(AddressDto.builder().content("testContent").build())
//                .id(22)
//                .branchStatus(BranchStatus.WAITING)
//                .build();
//    }
//    private Branch generateBranch() {
//        return Branch.builder()
//                .name("etilerşubesi")
//                .menu(Menu.builder().build())
//                .address(Address.builder().content("testContent").build())
//                .id(22)
//                .branchStatus(BranchStatus.WAITING)
//                .build();
//    }
//
//}