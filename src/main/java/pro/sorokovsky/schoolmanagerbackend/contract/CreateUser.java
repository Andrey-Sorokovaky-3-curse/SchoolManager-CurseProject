package pro.sorokovsky.schoolmanagerbackend.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;

@Schema(description = "Сутність для створення користувача", requiredMode = Schema.RequiredMode.REQUIRED)
public record CreateUser(
        @NotBlank(message = "{errors.user.login.none}")
        @Size(message = "{errors.user.login.size}", max = 50)
        @Schema(
                description = "Логін користувача",
                example = "andrey",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String login,

        @NotBlank(message = "{errors.user.password.none}")
        @Size(message = "{errors.user.password.size}", max = 300)
        @Schema(
                description = "Пароль користувача",
                example = "<PASSWORD>",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 300
        )
        String password,

        @NotBlank(message = "{errors.user.first-name.none}")
        @Size(message = "{errors.user.first-name.size}", max = 50)
        @Schema(
                description = "Ім'я користувача",
                example = "Андрій",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String firstName,

        @NotBlank(message = "{errors.user.last-name.none}")
        @Size(message = "{errors.user.last-name.size}", max = 50)
        @Schema(
                description = "Прізвище користувача",
                example = "Сороковський",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String lastName,

        @NotBlank(message = "{errors.user.middle-name.none}")
        @Size(message = "{errors.user.middle-name.size}", max = 50)
        @Schema(
                description = "По батькові користувача",
                example = "Іванович",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String middleName,

        @NotNull(message = "{errors.user.birthday.none}")
        @Schema(
                description = "день народження користувача",
                example = "2004-08-09",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "date",
                pattern = "yyyy-MM-dd"
        )
        Date birthday,

        @NotNull(message = "{errors.user.gender.none}")
        @Schema(
                description = "Стать користувача",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Gender gender,

        @NotBlank(message = "{errors.user.address.none}")
        @Size(message = "{errors.user.address.size}", max = 200)
        @Schema(
                description = "Адреса користувача",
                example = "м. Хмельницький, вул. Пілотська 76",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 200
        )
        String address
) {
}
