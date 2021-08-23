package com.restaurantapp.restapp.model.entity.enumerated;

import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.Locale;

@Component
public class StatusEnumConverter implements AttributeConverter<String, Status> {

    @Override
    public Status convertToDatabaseColumn(String s) {

        try {
            return Status.valueOf(s.toUpperCase(Locale.ENGLISH));

        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(Status status) {

        if (status == Status.APPROVED) return "APPROVED";
        if (status == Status.REJECTED) return "REJECTED";
        if (status == Status.WAITING) return "WAITING";

        else
        return null;
    }
}
