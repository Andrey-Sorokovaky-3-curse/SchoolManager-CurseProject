package pro.sorokovsky.schoolmanagerbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.CreateParent;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.GetParent;
import pro.sorokovsky.schoolmanagerbackend.contract.parent.UpdateParent;
import pro.sorokovsky.schoolmanagerbackend.exception.parent.ParentNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.ParentMapper;
import pro.sorokovsky.schoolmanagerbackend.service.ParentsService;

import java.util.List;

@RestController
@RequestMapping("parents")
@RequiredArgsConstructor
@Tag(name = "Батьки")
public class ParentsController {
    private final ParentsService service;
    private final ParentMapper mapper;

    @GetMapping
    @Operation(summary = "Батьки", description = "Отримує всіх батьків")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GetParent.class))
                    )
            ),
            @ApiResponse(
                    description = "Не авторизований",
                    responseCode = "400",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    public ResponseEntity<List<GetParent>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Батьки", description = "Отримує всіх батьків певного учня")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    description = "Не авторизований",
                    responseCode = "400",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @GetMapping("by-children/{childrenId:\\d+}")
    public ResponseEntity<List<GetParent>> getByChildren(@PathVariable Integer childrenId) {
        return ResponseEntity.ok(service.getByPupil(childrenId).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Батько чи матір", description = "Отримує батька чи матір за ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetParent.class)
                    )
            ),
            @ApiResponse(
                    description = "Не авторизований",
                    responseCode = "400",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    description = "Не знайдено",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @GetMapping("by-id/{childrenId:\\d+}")
    public ResponseEntity<GetParent> getById(@PathVariable Integer childrenId) {
        return ResponseEntity.ok(service.getById(childrenId).map(mapper::toGet).orElseThrow(ParentNotFoundException::new));
    }

    @Operation(summary = "Новий батько чи матір", description = "Створює батька чи матір")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне створення",
                    responseCode = "201",
                    headers = {
                            @Header(
                                    description = "Посилання на ресурс",
                                    name = HttpHeaders.LOCATION,
                                    example = "http://localhost/parents/by-id/1"
                            )
                    }
            ),
            @ApiResponse(
                    description = "Не авторизований",
                    responseCode = "401",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    description = "Вже існує",
                    responseCode = "409",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreateParent parent,
            UriComponentsBuilder uiBuilder
    ) {
        var created = service.create(parent);
        return ResponseEntity
                .created(uiBuilder.replacePath("parents/by-id/{id}").build(created.getId()))
                .build();
    }

    @Operation(summary = "Оновити батька чи матір", description = "Оновлює батька чи матір")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне оновлення",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetParent.class)
                    )
            ),
            @ApiResponse(
                    description = "Не авторизований",
                    responseCode = "401",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    description = "Не знайдено",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    description = "Конфлікт даних",
                    responseCode = "409",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PutMapping("{id:\\d+}")
    public ResponseEntity<GetParent> update(@PathVariable Integer id, @Valid @RequestBody UpdateParent parent) {
        return ResponseEntity.ok(mapper.toGet(service.update(id, parent)));
    }

    @Operation(summary = "Видалення батька чи матір", description = "Видаляє батька чи матір")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне видалення",
                    responseCode = "204"
            ),
            @ApiResponse(
                    description = "Не авторизований",
                    responseCode = "401",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    description = "Не знайдено",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    description = "Конфлікт даних",
                    responseCode = "409",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
