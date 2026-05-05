package pro.sorokovsky.schoolmanagerbackend.contract.classes;

import io.swagger.v3.oas.annotations.media.Schema;
import pro.sorokovsky.schoolmanagerbackend.contract.classtype.GetClassType;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.GetEmployee;

@Schema(description = "Клас", requiredMode = Schema.RequiredMode.REQUIRED)
public record GetClass(
        @Schema(
                description = "Унікальний ідентифікатор",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer id,

        GetEmployee curator,

        GetClassType classType,

        @Schema(
                description = "Літера класу",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "А"
        )
        Character letter,

        @Schema(
                description = "Рік навчання",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "5"
        )
        Integer studyYear,

        @Schema(
                description = "Рік початку навчання",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "2004"
        )
        Integer createdAtYear,

        @Schema(
                description = "Кількість учнів",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "20"
        )
        Integer pupilsCount
) {
}
