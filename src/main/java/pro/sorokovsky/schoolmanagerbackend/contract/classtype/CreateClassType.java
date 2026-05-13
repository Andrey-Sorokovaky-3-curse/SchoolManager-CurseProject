package pro.sorokovsky.schoolmanagerbackend.contract.classtype;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Створення типу класу", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateClassType(
        @Schema(
                description = "Ім'я типу класу",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Інклюзивний клас"
        )
        @NotBlank(message = "{errors.class-type.name.none}")
        @Size(max = 100, message = "{errors.class-type.name.size}")
        String name,

        @Schema(
                description = "Опис типу класу",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Клас має дітей з особливими потребами"
        )
        @NotBlank(message = "{errors.class-type.description.none}")
        @Size(max = 1000, message = "{errors.class-type.description.size}")
        String description
) {
}
