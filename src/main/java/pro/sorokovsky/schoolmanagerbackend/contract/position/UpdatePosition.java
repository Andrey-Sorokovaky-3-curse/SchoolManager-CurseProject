package pro.sorokovsky.schoolmanagerbackend.contract.position;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(description = "Оновлення посади", requiredMode = Schema.RequiredMode.REQUIRED)
public record UpdatePosition(
        @Schema(
                description = "Назва посади",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Директор"
        )
        @NotEmpty(message = "{errors.position.name.none}")
        @Size(message = "{errors.position.name.size}", min = 1, max = 100)
        String name,

        @Schema(
                description = "Оклад посади",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "10000"
        )
        @Min(value = 0, message = "{errors.position.salary.min}")
        BigDecimal salary
) {
}
