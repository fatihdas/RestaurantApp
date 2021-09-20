package com.restaurantapp.restapp.model.entity.enumerated;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RolesEnumConverterTest {

    @Spy
    private RolesEnumConverter rolesEnumConverter;

    @Test
    public void convertToDatabaseColumn() {
        UserRoles userRoles = rolesEnumConverter.convertToDatabaseColumn("admin");
        Assertions.assertEquals(UserRoles.ADMIN, userRoles);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToDatabaseColumn_Exception() {
        rolesEnumConverter.convertToDatabaseColumn("admıns");
    }

    @Test
    public void convertToEntityAttribute() {
        String userRole = rolesEnumConverter.convertToEntityAttribute(UserRoles.ADMIN);
        Assertions.assertEquals("ADMIN", userRole);
    }

    @Test
    public void convertToEntityAttribute_null() {
        rolesEnumConverter.convertToEntityAttribute(null);
    }
}