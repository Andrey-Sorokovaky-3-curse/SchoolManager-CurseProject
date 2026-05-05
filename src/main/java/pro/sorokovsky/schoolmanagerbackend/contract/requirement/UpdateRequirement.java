package pro.sorokovsky.schoolmanagerbackend.contract.requirement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "Оновлення вимоги", requiredMode = Schema.RequiredMode.REQUIRED)
public record UpdateRequirement(
        @Schema(
                description = "Назва вимоги.",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null",
                maxLength = 100
        )
        @NotEmpty(message = "{errors.requirement.name.none}")
        @Size(max = 100, message = "{errors.requirement.name.size}")
        String name,

        @Schema(
                description = "Опис вимоги.",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null",
                maxLength = 1000
        )
        @NotEmpty(message = "{errors.requirement.description.none}")
        @Size(max = 100, message = "{errors.requirement.description.size}")
        String description
) {
}
