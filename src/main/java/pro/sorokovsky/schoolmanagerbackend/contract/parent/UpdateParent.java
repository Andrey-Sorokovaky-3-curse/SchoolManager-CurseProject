package pro.sorokovsky.schoolmanagerbackend.contract.parent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Оновити батька чи матір", requiredMode = Schema.RequiredMode.REQUIRED)
public record UpdateParent(
        @Schema(
                description = "Місце роботи/посада",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 1000
        )
        @NotEmpty(message = "{errors.parents.job.none}")
        @Size(max = 1000, message = "{errors.parents.job.size}")
        String job,


        @Schema(
                description = "Номер телефону",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 20
        )
        @NotEmpty(message = "{errors.parents.phone.none}")
        @Pattern(
                regexp = "\\+38[0-9]{10}",
                message = "{errors.parents.phone.pattern}"
        )
        String phoneNumber
) {
}
