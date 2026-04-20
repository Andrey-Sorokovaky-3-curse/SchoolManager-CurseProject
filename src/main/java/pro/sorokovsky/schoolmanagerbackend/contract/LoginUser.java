package pro.sorokovsky.schoolmanagerbackend.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Данні для входу користувача", requiredMode = Schema.RequiredMode.REQUIRED)
public record LoginUser(
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
        String password
) {
}
