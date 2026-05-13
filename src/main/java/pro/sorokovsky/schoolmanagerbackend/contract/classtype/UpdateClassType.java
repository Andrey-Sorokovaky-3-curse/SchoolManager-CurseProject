package pro.sorokovsky.schoolmanagerbackend.contract.classtype;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "Оновити тип класу", requiredMode = Schema.RequiredMode.REQUIRED)
public record UpdateClassType(
        @Schema(
                description = "Ім'я типу класу",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "Інклюзивний клас"
        )
        @NotEmpty(message = "{errors.class-type.name.none}")
        @Size(max = 100, message = "{errors.class-type.name.size}")
        String name,

        @Schema(
                description = "Опис типу класу",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "Клас має дітей з особливими потребами"
        )
        @NotEmpty(message = "{errors.class-type.description.none}")
        @Size(max = 1000, message = "{errors.class-type.description.size}")
        String description
) {
}
