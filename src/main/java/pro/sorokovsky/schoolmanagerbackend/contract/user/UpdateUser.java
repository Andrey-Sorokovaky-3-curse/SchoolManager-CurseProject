package pro.sorokovsky.schoolmanagerbackend.contract.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pro.sorokovsky.schoolmanagerbackend.entity.Gender;

import java.sql.Date;

@Schema(description = "Оновлення користувача", requiredMode = Schema.RequiredMode.REQUIRED)
public record UpdateUser(
        @NotBlank(message = "{errors.user.login.none}")
        @Size(message = "{errors.user.login.size}", max = 50)
        @Schema(
                description = "Логін користувача",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 50
        )
        String login,

        @NotBlank(message = "{errors.user.first-name.none}")
        @Size(message = "{errors.user.first-name.size}", max = 50)
        @Schema(
                description = "Ім'я користувача",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 50
        )
        String firstName,

        @NotBlank(message = "{errors.user.last-name.none}")
        @Size(message = "{errors.user.last-name.size}", max = 50)
        @Schema(
                description = "Прізвище користувача",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 50
        )
        String lastName,

        @NotBlank(message = "{errors.user.middle-name.none}")
        @Size(message = "{errors.user.middle-name.size}", max = 50)
        @Schema(
                description = "По батькові користувача",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 50
        )
        String middleName,

        @NotNull(message = "{errors.user.birthday.none}")
        @Schema(
                description = "день народження користувача",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                format = "date",
                pattern = "yyyy-MM-dd"
        )
        Date birthday,

        @NotNull(message = "{errors.user.gender.none}")
        @Schema(
                description = "Стать користувача",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "null"
        )
        Gender gender,

        @NotBlank(message = "{errors.user.address.none}")
        @Size(message = "{errors.user.address.size}", max = 200)
        @Schema(
                description = "Адреса користувача",
                example = "null",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                maxLength = 200
        )
        String address
) {
}
