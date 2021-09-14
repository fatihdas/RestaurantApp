package com.restaurantapp.restapp.model.entity.enumerated;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.Locale;

@Component
public class StatusEnumConverter implements AttributeConverter<String, BranchStatus> {

    @SneakyThrows
    @Override
    public BranchStatus convertToDatabaseColumn(String s) {

        try {
            return BranchStatus.valueOf(s.toUpperCase(Locale.ENGLISH));

        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }
    }

    @Override
    public String convertToEntityAttribute(BranchStatus branchStatus) {

        if (branchStatus == BranchStatus.APPROVED) return "APPROVED";
        if (branchStatus == BranchStatus.REJECTED) return "REJECTED";
        if (branchStatus == BranchStatus.WAITING) return "WAITING";

        else {
            return null;
        }
    }
}
