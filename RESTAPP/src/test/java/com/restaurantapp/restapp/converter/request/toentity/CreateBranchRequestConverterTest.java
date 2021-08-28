package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateMenuRequestConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.entity.enumerated.Status;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateBranchRequestConverterTest {

    private static final String NAME = "etilerşubesi";
    private static final int ID = 25;
    private static final Status STATUS = Status.WAITING;

    @Mock
    private CreateMenuRequestConverter createMenuRequestConverter;

    @Mock
    CreateAddressRequestConverter createAddressRequestConverter;

    @InjectMocks
    private CreateBranchRequestConverter createBranchRequestConverter;

    @Test
    public void convert() {

        CreateBranchRequest branch = this.generateBranch();

        Mockito.when(createAddressRequestConverter.convert(Mockito.any(CreateAddressRequest.class)))
                .thenReturn(new Address());
        Mockito.when(createMenuRequestConverter.convert(Mockito.any(CreateMenuRequest.class)))
                .thenReturn(new Menu());
        Branch branchActual = createBranchRequestConverter.convert(branch);

        Assertions.assertEquals(ID, branchActual.getId());
        Assertions.assertEquals(NAME, branchActual.getName());
        Assertions.assertEquals(STATUS, branchActual.getStatus());
    }

    private CreateBranchRequest generateBranch() {
        return CreateBranchRequest.builder()
                .name(NAME)
                .createMenuRequest(CreateMenuRequest.builder()
                        .name("testmenu")
                        .id(2)
                        .build())
                .createAddressRequest(CreateAddressRequest.builder()
                        .id(33).content("testAddress")
                        .cityName("M.A.D")
                        .countyName("Süleymaniye")
                        .build())
                .id(ID)
                .status(STATUS)
                .build();
    }
}