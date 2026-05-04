package pro.sorokovsky.schoolmanagerbackend.contract.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import pro.sorokovsky.schoolmanagerbackend.contract.passport.GetPassport;
import pro.sorokovsky.schoolmanagerbackend.contract.position.GetPosition;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.GetRequirement;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.GetResponsibility;
import pro.sorokovsky.schoolmanagerbackend.entity.Gender;

import java.util.List;

@Schema(description = "Дані для отримання працівника")
public record GetEmployee(
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
                description = "Номер телефону",
                example = "+380673389286",
                requiredMode = Schema.RequiredMode.REQUIRED,
                maxLength = 20
        )
        String phoneNumber,

        List<GetPosition> positions,
        List<GetPassport> passports
) {
}
