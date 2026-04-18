package pro.sorokovsky.schoolmanagerbackend.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;

import java.time.LocalDate;

public record CreateUser(
        @NotBlank(message = "{errors.user.login.none}")
        @Size(message = "{errors.user.login.size}", max = 50)
        String login,

        @NotBlank(message = "{errors.user.password.none}")
        @Size(message = "{errors.user.password.size}", max = 300)
        String password,

        @NotBlank(message = "{errors.user.first-name.none}")
        @Size(message = "{errors.user.first-name.size}", max = 50)
        String firstName,

        @NotBlank(message = "{errors.user.last-name.none}")
        @Size(message = "{errors.user.last-name.size}", max = 50)
        String lastName,

        @NotBlank(message = "{errors.user.middle-name.none}")
        @Size(message = "{errors.user.middle-name.size}", max = 50)
        String middleName,

        @NotNull(message = "{errors.user.birthday.none}")
        LocalDate birthday,

        @NotNull(message = "{errors.user.gender.none}")
        Gender gender,

        @NotBlank(message = "{errors.user.address.none}")
        @Size(message = "{errors.user.address.size}", max = 200)
        String address
) {
}
