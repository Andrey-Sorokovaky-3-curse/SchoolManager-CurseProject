package pro.sorokovsky.schoolmanagerbackend.contract.passport;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сутність отримання паспортних даних")
public record GetPassport(
        @Schema(
                description = "Унікальний ідентифікатор",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer id,

        @Schema(
                description = "Назва паспорту",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Паспорт України"
        )
        String name,

        @Schema(
                description = "Паспортні дані",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String data
) {
}
