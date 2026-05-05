package pro.sorokovsky.schoolmanagerbackend.contract.requirement;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Створення вимоги", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateRequirement(
        @Schema(
                description = "Назва вимоги.",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Безпека дітей",
                maxLength = 100
        )
        @NotBlank(message = "{errors.requirement.name.none}")
        @Size(max = 100, message = "{errors.requirement.name.size}")
        String name,

        @Schema(
                description = "Опис вимоги.",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Відповідальність за дітей",
                maxLength = 1000
        )
        @NotBlank(message = "{errors.requirement.description.none}")
        @Size(max = 100, message = "{errors.requirement.description.size}")
        String description
) {
}
