package pro.sorokovsky.schoolmanagerbackend.contract.responsibility;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сутність для отримання відповідальності", requiredMode = Schema.RequiredMode.REQUIRED)
public record GetResponsibility(
        @Schema(
                description = "Унікальний ідентифікатор.",
                requiredMode =  Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Назва відповідальності.",
                requiredMode =  Schema.RequiredMode.REQUIRED,
                example = "Безпека дітей"
        )
        String name,

        @Schema(
                description = "Опис відповідальності.",
                requiredMode =  Schema.RequiredMode.REQUIRED,
                example = "Відповідальність за дітей"
        )
        String description
) {
}
