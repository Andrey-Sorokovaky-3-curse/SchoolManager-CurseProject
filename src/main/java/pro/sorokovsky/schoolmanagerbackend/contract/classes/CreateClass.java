package pro.sorokovsky.schoolmanagerbackend.contract.classes;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Створити клас", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateClass(
        @Schema(
                description = "Ідентифікатор класного керівника",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer curatorId,

        @Schema(
                description = "Ідентифікатор класного типу класу",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1"
        )
        Integer classTypeId,

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
                description = "Рік створення",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "2004"
        )
        Integer createdAtYear
) {
}
