package pro.sorokovsky.schoolmanagerbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sorokovsky.schoolmanagerbackend.contract.classes.GetClass;
import pro.sorokovsky.schoolmanagerbackend.contract.user.GetUser;
import pro.sorokovsky.schoolmanagerbackend.contract.user.UpdateUser;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserMapper;
import pro.sorokovsky.schoolmanagerbackend.service.UsersService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "Користувачі")
public class UsersController {
    private final UsersService service;
    private final UserMapper mapper;

    @PutMapping
    @Operation(summary = "Оновлення користувача", description = "Користувач може оновити себе")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне оновлення.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetClass.class)
                            )
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
                    responseCode = "409",
                    description = "Логін користувача вже є у систимі",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    public ResponseEntity<GetUser> update(
            @AuthenticationPrincipal UserEntity user,
            @Valid @RequestBody UpdateUser updateUser
    ) {
        return ResponseEntity.ok(mapper.toGet(service.update(user, updateUser)));
    }
}
