package com.restaurantapp.restapp.model.entity.enumerated;

import javax.persistence.AttributeConverter;
import java.util.Locale;

public class RolesEnumConverter implements AttributeConverter<String, Roles> {
    @Override
    public Roles convertToDatabaseColumn(String s) {

        try {
            return Roles.valueOf(s.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(Roles roles) {

        if (roles == Roles.ADMIN) return "ADMIN";
        if (roles == Roles.SELLER) return "SELLER";
        if (roles == Roles.BUYER) return "BUYER";

        else
        return null;
    }
}
