package com.restaurantapp.restapp.model.entity.enumerated;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.Locale;

@Component
public class RolesEnumConverter implements AttributeConverter<String, UserRoles> {
    @Override
    public UserRoles convertToDatabaseColumn(String s) {

        try {
            return UserRoles.valueOf(s.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    public String convertToEntityAttribute(UserRoles userRoles) {

        if (userRoles == UserRoles.ADMIN) return "ADMIN";
        if (userRoles == UserRoles.SELLER) return "SELLER";
        if (userRoles == UserRoles.BUYER) return "BUYER";

        else
        return null;
    }
}
