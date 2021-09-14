package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateAddressRequestConverterTest {

    private static final String CONTENT = "Tuzla";
    private static final long COUNTY_ID = 736L;
    private static final long USER_ID = 10L;
    private static final long BRANCH_ID = 2L;

    @Spy
    private CreateAddressRequestConverter createAddressRequestConverter;

    @Test
    public void whenConvertCalledByCreateUserAddressRequest_thenReturnAddress() {

        CreateAddressRequest address = this.generateUserAddress();
        Address addressActual = createAddressRequestConverter.convert(address);

        Assertions.assertEquals(COUNTY_ID, addressActual.getCounty().getId());
        Assertions.assertEquals(USER_ID, addressActual.getUser().getId());
        Assertions.assertEquals(CONTENT, addressActual.getContent());
    }
    @Test
    public void whenConvertCalledByCreateBranchAddressRequest_thenReturnAddress() {

        CreateAddressRequest address = this.generateBranchAddress();
        Address addressActual = createAddressRequestConverter.convert(address);

        Assertions.assertEquals(COUNTY_ID, addressActual.getCounty().getId());
        Assertions.assertEquals(BRANCH_ID, addressActual.getBranch().getId());
        Assertions.assertEquals(CONTENT, addressActual.getContent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenConvertCalledByNullRequest_thenThrowIllegalArgumentException() {
        createAddressRequestConverter.convert(null);

    }

    private CreateAddressRequest generateUserAddress() {
        return CreateAddressRequest.builder()
                .countyId(COUNTY_ID)
                .content(CONTENT)
                .userId(USER_ID)
                .build();
    }
    private CreateAddressRequest generateBranchAddress() {
        return CreateAddressRequest.builder()
                .countyId(COUNTY_ID)
                .content(CONTENT)
                .branchId(BRANCH_ID)
                .build();
    }
}
