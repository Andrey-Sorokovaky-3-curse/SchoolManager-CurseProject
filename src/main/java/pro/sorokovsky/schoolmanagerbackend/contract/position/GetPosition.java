package pro.sorokovsky.schoolmanagerbackend.contract.position;

import io.swagger.v3.oas.annotations.media.Schema;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.GetRequirement;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.GetResponsibility;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Сутність для отримання посади", requiredMode = Schema.RequiredMode.REQUIRED)
public record GetPosition(
        @Schema(
                description = "Унікальний ідентифікатор",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Назва посади",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Директор"
        )
        String name,

        @Schema(
                description = "Оклад посади",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "100000"
        )
        BigDecimal salary,

        List<GetRequirement> requirements,
        List<GetResponsibility> responsibilities
) {
}
