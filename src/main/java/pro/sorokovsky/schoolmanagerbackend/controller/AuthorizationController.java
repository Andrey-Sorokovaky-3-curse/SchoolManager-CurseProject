package pro.sorokovsky.schoolmanagerbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pro.sorokovsky.schoolmanagerbackend.contract.user.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.contract.user.GetUser;
import pro.sorokovsky.schoolmanagerbackend.contract.authorization.LoginUser;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserMapper;
import pro.sorokovsky.schoolmanagerbackend.service.AuthorizationService;

@Tag(name = "Авторизація")
@RequiredArgsConstructor
@RestController
@RequestMapping("authorization")
public class AuthorizationController {
    private final AuthorizationService service;
    private final UserMapper mapper;

    @Operation(summary = "Реєстрація", description = "Реєстрація користувача у системі.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успішна реєстрація.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    ),
                    headers = {
                            @Header(
                                    name = HttpHeaders.AUTHORIZATION,
                                    description = "Токен доступу.",
                                    example = "Bearer <TOKEN>"
                            ),
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "Посилання на себе.",
                                    example = "https://localhost/authorization/profile"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некоректні данні.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Вже авторизований.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Користувач вже є у системі.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PostMapping("register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody CreateUser newUser,
            HttpServletResponse response,
            @NonNull UriComponentsBuilder uriBuilder
    ) {
        service.register(newUser, response);
        return  ResponseEntity.created(uriBuilder.replacePath("profile").build().toUri()).build();
    }

    @Operation(summary = "Вхід", description = "Вхід користувача у систему.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успішний вхід.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    ),
                    headers = {
                            @Header(
                                    name = HttpHeaders.AUTHORIZATION,
                                    description = "Токен доступу.",
                                    example = "Bearer <TOKEN>"
                            ),
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Некоректні данні.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Вже авторизований.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Користувача не знайдено у системі.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PostMapping("login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginUser loginUser, HttpServletResponse response) {
        service.login(loginUser, response);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримання себе", description = "Отримання авторизованого користувача.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetUser.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = " Користувача не знайдено.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @GetMapping("profile")
    public ResponseEntity<GetUser> getProfile(@AuthenticationPrincipal @NonNull UserEntity user) {
        return ResponseEntity.ok(mapper.toGet(user));
    }

    @Operation(summary = "Вихід", description = "Вихід користувача із системи.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успішний вихід."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = " Користувача не знайдено.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @DeleteMapping("logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        service.logout(response);
        return ResponseEntity.noContent().build();
    }
}
