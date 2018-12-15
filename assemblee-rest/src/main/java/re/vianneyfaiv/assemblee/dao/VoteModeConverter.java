package re.vianneyfaiv.assemblee.dao;

import re.vianneyfaiv.assemblee.model.pojo.VoteMode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class VoteModeConverter implements AttributeConverter<VoteMode, String> {

    @Override
    public String convertToDatabaseColumn(VoteMode value) {
        if (value == null) {
            return null;
        }

        return value.getCode();
    }

    @Override
    public VoteMode convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return VoteMode.fromCode(value);
    }
}
