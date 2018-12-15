package re.vianneyfaiv.assemblee.dao;

import re.vianneyfaiv.assemblee.model.pojo.PoliticalBodyType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PoliticalBodyTypeConverter implements AttributeConverter<PoliticalBodyType, String> {

    @Override
    public String convertToDatabaseColumn(PoliticalBodyType value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    @Override
    public PoliticalBodyType convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return PoliticalBodyType.fromCode(value);
    }
}
