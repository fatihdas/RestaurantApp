package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateBranchRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateMenuRequestConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.Branch;
import com.restaurantapp.restapp.model.entity.Menu;
import com.restaurantapp.restapp.model.entity.enumerated.BranchStatus;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.create.CreateBranchRequest;
import com.restaurantapp.restapp.model.request.create.CreateMenuRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateBranchRequestConverterTest {

    private static final String NAME = "etilerşubesi";
    private static final int ID = 25;
    private static final BranchStatus BRANCH_STATUS = BranchStatus.WAITING;

    @Mock
    CreateAddressRequestConverter createAddressRequestConverter;

    @InjectMocks
    private CreateBranchRequestConverter createBranchRequestConverter;

    @Test
    public void whenConvertCalledByCreateBranchRequest_thenReturnBranch() {

        CreateBranchRequest branch = this.generateBranch();

        Mockito.when(createAddressRequestConverter.convert(Mockito.any(CreateAddressRequest.class)))
                .thenReturn(new Address());
        Branch branchActual = createBranchRequestConverter.convert(branch);

        Assertions.assertEquals(ID, branchActual.getId());
        Assertions.assertEquals(NAME, branchActual.getName());
        Assertions.assertEquals(BRANCH_STATUS, branchActual.getBranchStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConvertCalledByNullRequest_thenThrowIllegalArgumentException(){
        createBranchRequestConverter.convert(null);
    }

    private CreateBranchRequest generateBranch() {
        return CreateBranchRequest.builder()
                .name(NAME)
                .createAddressRequest(CreateAddressRequest.builder()
                        .build())
                .id(ID)
                .build();
    }
}