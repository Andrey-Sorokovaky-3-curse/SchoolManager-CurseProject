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
import pro.sorokovsky.schoolmanagerbackend.contract.classtype.CreateClassType;
import pro.sorokovsky.schoolmanagerbackend.contract.classtype.GetClassType;
import pro.sorokovsky.schoolmanagerbackend.contract.classtype.UpdateClassType;
import pro.sorokovsky.schoolmanagerbackend.exception.сlasstype.ClassTypeNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.ClassTypeMapper;
import pro.sorokovsky.schoolmanagerbackend.service.ClassTypesService;

import java.util.List;

@RestController
@RequestMapping("class-types")
@Tag(name = "Типи класу")
@RequiredArgsConstructor
public class ClassTypesController {
    private final ClassTypesService service;
    private final ClassTypeMapper mapper;

    @Operation(summary = "Типи класів", description = "Отримає всі типи класів")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GetClassType.class))
                    )
            ),
            @ApiResponse(
                    description = "Не авторизований",
                    responseCode = "401",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<GetClassType>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Тип класу", description = "Отримає тип класу за ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetClassType.class)
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
            )
    })
    @GetMapping("by-id/{id:\\d++}")
    public ResponseEntity<GetClassType> getById(@PathVariable Integer id) {
         return ResponseEntity.ok(service.getById(id).map(mapper::toGet).orElseThrow(ClassTypeNotFoundException::new));
    }

    @Operation(summary = "Новий тип класу", description = "Створює тип класу")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне створення",
                    responseCode = "201",
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    example = "http://localhost:8080/class-types/by-id/1",
                                    description = "Посилання на тип клас"
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
                    description = "Не знайдено",
                    responseCode = "404",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<GetClassType> create(
            @Valid @RequestBody CreateClassType classType,
            UriComponentsBuilder uriBuilder
    ) {
        var created = service.create(classType);
        return ResponseEntity
                .created(uriBuilder.replacePath("class-types/by-id/{id}").build(created.getId()))
                .build();
    }

    @Operation(summary = "Оновити тип класу", description = "Оновлює тип класу")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне оновлення",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetClassType.class)
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
            )
    })
    @PutMapping("{id:\\d+}")
    public ResponseEntity<GetClassType> update(@PathVariable Integer id, @Valid @RequestBody UpdateClassType classType) {
        return ResponseEntity
                .ok(mapper.toGet(service.update(id, classType)));
    }

    @Operation(summary = "Видалення тип класу", description = "Видаляє тип класу")
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
            )
    })
    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
