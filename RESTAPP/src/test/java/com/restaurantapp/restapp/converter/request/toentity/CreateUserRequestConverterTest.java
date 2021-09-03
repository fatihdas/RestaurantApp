//package com.restaurantapp.restapp.converter.request.toentity;
//
//import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
//import com.restaurantapp.restapp.model.converter.create.request.CreateUserRequestConverter;
//import com.restaurantapp.restapp.model.entity.Address;
//import com.restaurantapp.restapp.model.entity.User;
//import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
//import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
//import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CreateUserRequestConverterTest {
//
//    private static final int ID = 111;
//    private static final UserRoles USER_ROLES = UserRoles.ADMIN;
//    private static final String EMAIL = "testmail";
//    private static final String NAME = "Reddington";
//    private static final String PASSWORD = "eliz";
//
//    @Mock
//    private CreateAddressRequestConverter createAddressRequestConverter;
//
//    @Spy
//    @InjectMocks
//    private CreateUserRequestConverter createUserRequestConverter;
//
//    @Test
//    public void convert() {
//
//        CreateUserRequest user = this.generateUser();
//        Mockito.when(createAddressRequestConverter.convert(Mockito.any(CreateAddressRequest.class)))
//                .thenReturn(new Address());
//        User userActaul = createUserRequestConverter.convert(user);
//
//        Assertions.assertEquals(ID, userActaul.getId());
//        Assertions.assertEquals(NAME, userActaul.getName());
//        Assertions.assertEquals(EMAIL, userActaul.getEmail());
//        Assertions.assertEquals(PASSWORD, userActaul.getPassword());
//        Assertions.assertEquals(USER_ROLES, userActaul.getRoles().get(0));
//    }
//
//    private CreateUserRequest generateUser() {
//
//        return CreateUserRequest.builder()
//                .id(ID)
//                .userRoles(USER_ROLES)
//                .email(EMAIL)
//                .name(NAME)
//                .password(PASSWORD)
//                .createAddressRequest(new CreateAddressRequest())
//                .build();
//    }
//}