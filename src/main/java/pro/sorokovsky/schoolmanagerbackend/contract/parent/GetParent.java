package pro.sorokovsky.schoolmanagerbackend.contract.parent;

import io.swagger.v3.oas.annotations.media.Schema;
import pro.sorokovsky.schoolmanagerbackend.entity.Gender;

@Schema(description = "Батьки", requiredMode = Schema.RequiredMode.REQUIRED)
public record GetParent(
        @Schema(
                description = "Унікальний ідентифікатор",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Integer id,

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
        java.time.LocalDate birthday,

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
        String address,

        @Schema(
                description = "Місце роботи/посада",
                example = "фрілансер",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String job,


        @Schema(
                description = "Номер телефону",
                example = "+380673389286",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String phoneNumber
) {
}
