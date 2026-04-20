package pro.sorokovsky.schoolmanagerbackend.converter;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.Schema;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;

import java.util.Iterator;
import java.util.List;

@Component
public class GenderModelConverter implements ModelConverter {
    @Override
    public Schema resolve(@NonNull AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
        if (type.getType() == Gender.class) {
            final var schema = new BooleanSchema();
            schema.setDescription("Стать користувача (false - чоловіча, true - жіноча)");
            schema.setExample(Gender.MALE.getValue());
            schema.setEnum(List.of(Gender.MALE.getValue(), Gender.FEMALE.getValue()));
            return schema;

        }
        if (chain.hasNext()) {
            return chain.next().resolve(type, context, chain);
        }
        return null;
    }
}
