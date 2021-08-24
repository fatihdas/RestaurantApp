package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.entity.enumerated.Status;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateBranchRequestConverterTest {


    @Spy
    @InjectMocks
    private CreateBranchRequestConverter createBranchRequestConverter;

    @Test
    public void convert() {

        Branch branchExpected = this.generateBranch();

//        Mockito.when(createAddressRequestConverter.convert(Mockito.any(CreateAddressRequest.class)))
//                .thenReturn(branchExpected.getAddress());
//        Mockito.when(createMenuRequestConverter.convert(Mockito.any(CreateMenuRequest.class)))
//                .thenReturn(branchExpected.getMenu());
        Mockito.doReturn(branchExpected).when(createBranchRequestConverter).convert(Mockito.any(CreateBranchRequest.class));

        Branch branchActual = createBranchRequestConverter.convert(new CreateBranchRequest());
        Assertions.assertEquals(branchExpected, branchActual);
    }

    private Branch generateBranch() {
        return Branch.builder()
                .name("etilerşubesi")
                .menu(Menu.builder()
                        .name("testmenu")
                        .id(2)
                        .build())
                .address(Address.builder()
                        .id(33).content("testAddress")
                        .cityName("M.A.D")
                        .countyName("Süleymaniye")
                        .build())
                .id(25)
                .status(Status.WAITING)
                .build();
    }
}