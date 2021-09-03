//package com.restaurantapp.restapp.converter.entity.todto;
//
//import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
//import com.restaurantapp.restapp.model.converter.entity.todto.MenuEntityToDtoConverter;
//import com.restaurantapp.restapp.model.dto.AddressDto;
//import com.restaurantapp.restapp.model.dto.BranchDto;
//import com.restaurantapp.restapp.model.dto.MenuDto;
//import com.restaurantapp.restapp.model.entity.Address;
//import com.restaurantapp.restapp.model.entity.Branch;
//import com.restaurantapp.restapp.model.entity.Menu;
//import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class BranchEntityToDtoConverterTest {
//
//    private static final String NAME = "etilerşubesi";
//    private static final int ID = 25;
//    private static final BranchStatus BRANCH_STATUS = BranchStatus.WAITING;
//
//    @Mock
//    private MenuEntityToDtoConverter menuEntityToDtoConverter;
//
//    @Mock
//    private AddressEntityToDtoConverter addressEntityToDtoConverter;
//
//    @InjectMocks
//    private BranchEntityToDtoConverter branchEntityToDtoConverter;
//
//    @Test
//    public void convert() {
//
//        Branch branch = this.generateBranch();
//        Mockito.when(menuEntityToDtoConverter.convert(Mockito.any(Menu.class))).thenReturn(new MenuDto());
//        Mockito.when(addressEntityToDtoConverter.convert(Mockito.any(Address.class))).thenReturn(new AddressDto());
//        BranchDto branchActual = branchEntityToDtoConverter.convert(branch);
//
//        Assertions.assertEquals(ID, branchActual.getId());
//        Assertions.assertEquals(NAME, branchActual.getName());
//        Assertions.assertEquals(BRANCH_STATUS, branchActual.getBranchStatus());
//    }
//
//    private Branch generateBranch() {
//
//        return Branch.builder()
//                .name(NAME)
//                .menu(Menu.builder().build().builder()
//                        .name("testmenu")
//                        .id(2)
//                        .build())
//                .address(Address.builder()
//                        .id(33).content("testAddress")
//                        .cityName("M.A.D")
//                        .countyName("Süleymaniye")
//                        .build())
//                .id(ID)
//                .branchStatus(BRANCH_STATUS)
//                .build();
//    }
//}