package com.example.asansorapijava.enumerate.converter;

import com.example.asansorapijava.enumerate.Color;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ColorConverter implements AttributeConverter<Color,Integer> {
    @Override
    public Integer convertToDatabaseColumn(Color color) {
        if(color==null) return null;
        return color.getID();
    }

    @Override
    public Color convertToEntityAttribute(Integer databaseValue) {
        if(databaseValue==null) return null;
        return Color.getColor(databaseValue);
    }
}