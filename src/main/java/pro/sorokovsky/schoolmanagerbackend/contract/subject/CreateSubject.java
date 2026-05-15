package pro.sorokovsky.schoolmanagerbackend.contract.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Створити предмет", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateSubject(
        @Schema(
                description = "Назва предмету",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Математика",
                maxLength = 50
        )
        @NotBlank(message = "{errors.subject.name.none}")
        @Size(max = 50, message = "{errors.subject.name.size}")
        String name,

        @Schema(
                description = "Опис предмету",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Математика вчить рахувати",
                maxLength = 500
        )
        @NotBlank(message = "{errors.subject.description.none}")
        @Size(max = 500, message = "{errors.subject.description.size}")
        String description,

        @Schema(
                description = "Ідентифікатор вчителя",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer teacherId
) {
}
