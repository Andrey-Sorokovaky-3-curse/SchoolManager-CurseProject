package pro.sorokovsky.schoolmanagerbackend.contract.responsibility;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "Оновлення відповідальності", requiredMode = Schema.RequiredMode.REQUIRED)
public record UpdateResponsibility(
        @Schema(
                description = "Назва відповідальності",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null",
                maxLength = 100
        )
        @NotEmpty(message = "{errors.responsibility.name.none}")
        @Size(max = 100, message = "{errors.responsibility.name.size}")
        String name,

        @Schema(
                description = "Опис відповідальності",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null",
                maxLength = 1000
        )
        @NotEmpty(message = "{errors.responsibility.description.none}")
        @Size(max = 100, message = "{errors.responsibility.description.size}")
        String description
) {
}
