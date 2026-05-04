package pro.sorokovsky.schoolmanagerbackend.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Boolean> {
    @Override
    public Boolean convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Boolean data) {
        if (data == null) {
            return null;
        }
        return Gender.fromValue(data);
    }
}
