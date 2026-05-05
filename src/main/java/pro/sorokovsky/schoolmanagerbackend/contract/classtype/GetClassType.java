package pro.sorokovsky.schoolmanagerbackend.contract.classtype;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Тип класу",
        requiredMode = Schema.RequiredMode.REQUIRED
)
public record GetClassType(
        @Schema(
                description = "Унікальний ідентифікатор",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Ім'я типу класу",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Інклюзивний клас"
        )
        String name,

        @Schema(
                description = "Опис типу класу",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Клас має дітей з особливими потребами"
        )
        String description
) {
}
