package re.vianneyfaiv.assemblee.dao;

import re.vianneyfaiv.assemblee.model.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender value) {
        if (value == null) {
            return null;
        }

        return value.getCivilite();
    }

    @Override
    public Gender convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Gender.fromCivilite(value);
    }
}
