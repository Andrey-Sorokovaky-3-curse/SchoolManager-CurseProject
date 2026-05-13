package pro.sorokovsky.schoolmanagerbackend.contract.parent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Створити батька чи матір")
public record CreateParent(
        @Schema(
                description = "Ідентифікатор користувача",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "{errors.parents.already-exists}")
        Integer userId,

        @Schema(
                description = "Місце роботи/посада",
                example = "фрілансер",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "{errors.parents.job.none}")
        @Size(max = 1000, message = "{errors.parents.job.size}")
        String job,


        @Schema(
                description = "Номер телефону",
                example = "+380673389286",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "{errors.parents.phone.none}")
        @Pattern(
                regexp = "\\+38[0-9]{10}",
                message = "{errors.parents.phone.pattern}"
        )
        String phoneNumber
) {
}
