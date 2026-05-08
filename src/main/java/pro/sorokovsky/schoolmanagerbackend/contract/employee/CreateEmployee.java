package pro.sorokovsky.schoolmanagerbackend.contract.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Створення працівника", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateEmployee(
        @Schema(
                description = "Ідентифікатор користувача",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "{errors.employee.user-id.none}")
        Integer userId,

        @Schema(
                description = "Номер телефону",
                example = "+380673389286",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 20
        )
        @NotNull(message = "{errors.employee.phone.none}")
        @Pattern(
                regexp = "\\+38[0-9]{10}",
                message = "{errors.employee.phone.format}"
        )
        String phoneNumber
) {
}
