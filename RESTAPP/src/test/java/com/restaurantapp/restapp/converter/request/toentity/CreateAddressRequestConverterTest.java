package com.restaurantapp.restapp.converter.request.toentity;

import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreateAddressRequestConverterTest {

    @Spy
    private CreateAddressRequestConverter createAddressRequestConverter;


    @org.junit.Test
    public void testConvert() {

        Address addressExpected = this.generateAddress();
        Mockito.doReturn(addressExpected).when(createAddressRequestConverter).convert(Mockito.any(CreateAddressRequest.class));
        Address addressActual = createAddressRequestConverter.convert(new CreateAddressRequest());

        Assertions.assertEquals(addressExpected, addressActual);
    }

    private Address generateAddress() {
        return Address.builder()
                .id(2)
                .countyName("kadıköy")
                .cityName("İstanbul")
                .content("moda")
                .build();
    }
}
