//package com.restaurantapp.restapp.converter.request.toentity;
//
//import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
//import com.restaurantapp.restapp.model.entity.Address;
//import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CreateAddressRequestConverterTest {
//
//    private static final int ID = 2;
//    private static final String COUNTY_NAME = "kadıköy";
//    private static final String CITY_NAME = "İstanbul";
//    private static final String CONTENT = "moda";
//
//    @Spy
//    private CreateAddressRequestConverter createAddressRequestConverter;
//
//    @Test
//    public void testConvert() {
//
//        CreateAddressRequest address = this.generateAddress();
//        Address addressActual = createAddressRequestConverter.convert(address);
//
//        Assertions.assertEquals(ID, addressActual.getId());
//        Assertions.assertEquals(COUNTY_NAME, addressActual.getCountyName());
//        Assertions.assertEquals(CITY_NAME, addressActual.getCityName());
//        Assertions.assertEquals(CONTENT, addressActual.getContent());
//    }
//
//    private CreateAddressRequest generateAddress() {
//        return CreateAddressRequest.builder()
//                .id(ID)
//                .countyName(COUNTY_NAME)
//                .cityName(CITY_NAME)
//                .content(CONTENT)
//                .build();
//    }
//}
