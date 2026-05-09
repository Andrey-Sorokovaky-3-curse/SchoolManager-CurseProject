package pro.sorokovsky.schoolmanagerbackend.contract.passport;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Створення паспорту", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreatePassport(
        @Schema(
                description = "Назва паспорту",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Паспорт України",
                maxLength = 30
        )
        @NotBlank(message = "{errors.passport.name.none}")
        @Size(max = 30, message = "{errors.passport.name.size}")
        String name,

        @Schema(
                description = "Паспортні данні",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Паспорт україни виданий у Києві",
                maxLength = 1000
        )
        @NotBlank(message = "{errors.passport.data.none}")
        @Size(max = 1000, message = "{errors.passport.data.size}")
        String data
) {
}
