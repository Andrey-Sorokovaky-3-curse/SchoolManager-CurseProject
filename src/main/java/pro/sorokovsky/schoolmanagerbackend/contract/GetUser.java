package pro.sorokovsky.schoolmanagerbackend.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;

import java.sql.Date;

@Schema(description = "Сутність для отримання користувача", requiredMode = Schema.RequiredMode.REQUIRED)
public record GetUser(
        @Schema(
                description = "Унікальний ідентифікатор користувача",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long id,

        @Schema(
                description = "Логін користувача",
                example = "andrey",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String login,

        @Schema(
                description = "Ім'я користувача",
                example = "Андрій",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String firstName,

        @Schema(
                description = "Прізвище користувача",
                example = "Сороковський",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String lastName,

        @Schema(
                description = "По батькові користувача",
                example = "Іванович",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 50
        )
        String middleName,

        @Schema(
                description = "день народження користувача",
                example = "2004-08-09",
                requiredMode = Schema.RequiredMode.REQUIRED,
                format = "date",
                pattern = "yyyy-MM-dd"
        )
        Date birthday,

        @Schema(
                description = "Стать користувача",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Gender gender,

        @Schema(
                description = "Адреса користувача",
                example = "м. Хмельницький, вул. Пілотська 76",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 200
        )
        String address
) {
}
