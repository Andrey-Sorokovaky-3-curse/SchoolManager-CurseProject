package pro.sorokovsky.schoolmanagerbackend.contract.subject;

import io.swagger.v3.oas.annotations.media.Schema;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.GetEmployee;

@Schema(description = "Предмет", requiredMode = Schema.RequiredMode.REQUIRED)
public record GetSubject(
        @Schema(
                description = "Унікальний ідентифікатор",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Назва предмету",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "Математика"
        )
        String name,

        @Schema(
                description = "Опис предмету",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "Математика вчить рахувати"
        )
        String description,

        GetEmployee teacher
) {
}
