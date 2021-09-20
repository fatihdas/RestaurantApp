package com.restaurantapp.restapp.model.entity.enumerated;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatusEnumConverterTest {

    @Spy
    private StatusEnumConverter statusEnumConverter;

    @Test
    public void convertToDatabaseColumn() {
        BranchStatus status = statusEnumConverter.convertToDatabaseColumn("approved");
        Assertions.assertEquals(BranchStatus.APPROVED, status);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertToDatabaseColumn_Exception() {
        statusEnumConverter.convertToDatabaseColumn("test");
    }


    @Test
    public void convertToEntityAttribute() {
        statusEnumConverter.convertToEntityAttribute(null);

    }
}