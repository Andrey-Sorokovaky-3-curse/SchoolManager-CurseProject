package pro.sorokovsky.schoolmanagerbackend.contract.pupil;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Створення учня", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreatePupil(
        @Schema(
                description = "Додаткова інформація",
                example = "Має інвалідність",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "{errors.pupil.extra.none}")
        @Size(message = "{errors.pupil.extra.size}")
        String extraInformation,

        @Schema(
                description = "Ідентифікатор користувача",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer userId,

        @Schema(
                description = "Ідентифікатор матері",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer motherId,

        @Schema(
                description = "Ідентифікатор батька",
                example = "3",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer fatherId,

        @Schema(
                description = "Ідентифікатор класу",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer classId
) {
}
