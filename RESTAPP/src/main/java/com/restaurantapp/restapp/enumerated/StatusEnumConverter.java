package com.restaurantapp.restapp.enumerated;

import com.restaurantapp.restapp.model.entity.enumerated.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class StatusEnumConverter implements Converter<String,Status> {
    @Override
    public Status convert(String s) {
        try {
            return Status.valueOf(s.toUpperCase(Locale.ENGLISH));

        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
