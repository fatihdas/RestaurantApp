package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.BranchIdToEntityConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.entity.enumerated.Status;
import com.restaurantapp.restapp.service.impl.BranchServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchIdToEntityConverterTest {

    private static final String NAME = "TestBranch";
    private static final long ID = 12345;
    private static final Status STATUS = Status.WAITING;

    @Mock
    private BranchServiceImpl branchService;

    @InjectMocks
    private BranchIdToEntityConverter branchIdToEntityConverter;

    @Test
    public void testConvert() {

        Branch branch = this.generateBranch();

        Mockito.when(branchService.getBranchByid(Mockito.anyLong())).thenReturn(branch);

        Branch branchActual = branchIdToEntityConverter.convert(15);

        Assertions.assertEquals(ID, branchActual.getId());
        Assertions.assertEquals(NAME, branchActual.getName());
        Assertions.assertEquals(STATUS, branchActual.getStatus());
    }

    private Branch generateBranch() {
        return Branch.builder()
                .name(NAME)
                .menu(Menu.builder().build().builder()
                        .name("testmenu")
                        .id(2)
                        .build())
                .address(Address.builder()
                        .id(33).content("testAddress")
                        .cityName("M.A.D")
                        .countyName("Süleymaniye")
                        .build())
                .id(ID)
                .status(STATUS)
                .build();
    }

}