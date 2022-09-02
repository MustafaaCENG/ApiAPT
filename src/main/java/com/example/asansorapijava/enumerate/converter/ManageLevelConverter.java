package com.example.asansorapijava.enumerate.converter;

import com.example.asansorapijava.enumerate.ManageLevel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)

public class ManageLevelConverter implements AttributeConverter<ManageLevel,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ManageLevel manageLevel) {
        if(manageLevel==null) return null;
        return manageLevel.getID();
    }

    @Override
    public ManageLevel convertToEntityAttribute(Integer databaseValue) {
        if(databaseValue==null) return null;
        return ManageLevel.getManagerLevel(databaseValue);
    }
}
