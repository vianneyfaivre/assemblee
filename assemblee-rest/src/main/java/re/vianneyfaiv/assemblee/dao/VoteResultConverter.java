package re.vianneyfaiv.assemblee.dao;

import re.vianneyfaiv.assemblee.model.pojo.VoteResult;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class VoteResultConverter implements AttributeConverter<VoteResult, String> {

    @Override
    public String convertToDatabaseColumn(VoteResult value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    @Override
    public VoteResult convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return VoteResult.fromCode(value);
    }
}
