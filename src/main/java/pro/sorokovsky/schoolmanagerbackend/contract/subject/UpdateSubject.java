package pro.sorokovsky.schoolmanagerbackend.contract.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "Оновити предмет", requiredMode = Schema.RequiredMode.REQUIRED)
public record UpdateSubject(
        @Schema(
                description = "Назва предмету",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null",
                maxLength = 50
        )
        @NotEmpty(message = "{errors.subject.name.none}")
        @Size(max = 50, message = "{errors.subject.name.size}")
        String name,

        @Schema(
                description = "Опис предмету",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null",
                maxLength = 500
        )
        @NotEmpty(message = "{errors.subject.description.none}")
        @Size(max = 500, message = "{errors.subject.description.size}")
        String description,

        @Schema(
                description = "Ідентифікатор вчителя",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null"
        )
        Integer teacherId
) {
}
