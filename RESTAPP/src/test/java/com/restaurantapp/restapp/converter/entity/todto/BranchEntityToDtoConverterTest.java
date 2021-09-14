package com.restaurantapp.restapp.converter.entity.todto;

import com.restaurantapp.restapp.model.converter.entity.todto.AddressEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.BranchEntityToDtoConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.MenuEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.BranchDto;
import com.restaurantapp.restapp.model.dto.MenuDto;
import com.restaurantapp.restapp.model.entity.*;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BranchEntityToDtoConverterTest {

    private static final String NAME = "etilerşubesi";
    private static final int ID = 25;
    private static final BranchStatus BRANCH_STATUS = BranchStatus.WAITING;
    private static final String MENU_NAME = "testmenu";
    private static final int MENU_ID = 2;
    private static final int ADDRESS_ID = 33;
    private static final String CONTENT = "testAddress";
    private static final String COUNTY_NAME = "Süleymaniye";
    private static final long RESTAURANT_ID = 1L;

    @Mock
    private MenuEntityToDtoConverter menuEntityToDtoConverter;

    @Mock
    private AddressEntityToDtoConverter addressEntityToDtoConverter;

    @InjectMocks
    private BranchEntityToDtoConverter branchEntityToDtoConverter;

    @Test
    public void whenConvertCalledByBranch_thenReturnBranchDto() {

        Branch branch = this.generateBranch();
        BranchDto branchActual = branchEntityToDtoConverter.convert(branch);

        Assertions.assertEquals(ID, branchActual.getId());
        Assertions.assertEquals(NAME, branchActual.getName());
        Assertions.assertEquals(BRANCH_STATUS, branchActual.getBranchStatus());
    }

    private Branch generateBranch() {

        return Branch.builder()
                .name(NAME)
                .menu(Menu.builder().build().builder()
                        .name(MENU_NAME)
                        .id(MENU_ID)
                        .build())
                .address(Address.builder()
                        .id(ADDRESS_ID)
                        .content(CONTENT)
                        .county(County.builder()
                                .name(COUNTY_NAME)
                                .build())
                        .build())
                .id(ID)
                .branchStatus(BRANCH_STATUS)
                .restaurant(Restaurant.builder()
                        .id(RESTAURANT_ID)
                        .build())
                .build();
    }
}