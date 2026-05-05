package pro.sorokovsky.schoolmanagerbackend.contract.responsibility;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Створення відповідальності", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateResponsibility(
        @Schema(
                description = "Назва відповідальності",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Безпека дітей",
                maxLength = 100
        )
        @NotBlank(message = "{errors.responsibility.name.none}")
        @Size(max = 100, message = "{errors.responsibility.name.size}")
        String name,

        @Schema(
                description = "Опис відповідальності",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Відповідальність за дітей",
                maxLength = 1000
        )
        @NotBlank(message = "{errors.responsibility.description.none}")
        @Size(max = 100, message = "{errors.responsibility.description.size}")
        String description
) {
}
