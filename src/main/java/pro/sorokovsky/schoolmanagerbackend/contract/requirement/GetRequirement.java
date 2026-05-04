package pro.sorokovsky.schoolmanagerbackend.contract.requirement;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сутність для отримання вимоги", requiredMode = Schema.RequiredMode.REQUIRED)
public record GetRequirement(
        @Schema(
                description = "Унікальний ідентифікатор.",
                requiredMode =  Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Назва вимоги.",
                requiredMode =  Schema.RequiredMode.REQUIRED,
                example = "Безпека дітей"
        )
        String name,

        @Schema(
                description = "Опис вимоги.",
                requiredMode =  Schema.RequiredMode.REQUIRED,
                example = "Відповідальність за дітей"
        )
        String description
) {
}
