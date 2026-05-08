package pro.sorokovsky.schoolmanagerbackend.contract.position;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(description = "Створення посади", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreatePosition(
        @Schema(
                description = "Назва посади",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Директор"
        )
        @NotBlank(message = "{errors.position.name.none}")
        @Size(message = "{errors.position.name.size}", min = 1, max = 100)
        String name,

        @Schema(
                description = "Оклад посади",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "10000"
        )
        @NotNull(message = "{errors.position.salary.none}")
        @Min(value = 0, message = "{errors.position.salary.min}")
        BigDecimal salary
) {
}
