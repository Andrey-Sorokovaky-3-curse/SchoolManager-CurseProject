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
import pro.sorokovsky.schoolmanagerbackend.contract.classes.CreateClass;
import pro.sorokovsky.schoolmanagerbackend.contract.classes.GetClass;
import pro.sorokovsky.schoolmanagerbackend.exception.clazz.ClassNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.ClassMapper;
import pro.sorokovsky.schoolmanagerbackend.service.ClassesService;

import java.util.List;

@RestController
@RequestMapping("classes")
@RequiredArgsConstructor
@Tag(name = "Класи")
public class ClassesController {
    private final ClassesService service;
    private final ClassMapper mapper;

    @GetMapping("/by-study-year/{year:\\d+}")
    @Operation(summary = "Список класів", description = "Список класів за роком навчання")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання.",
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
    })
    public ResponseEntity<List<GetClass>> getByStudyYear(@PathVariable Integer year) {
        return ResponseEntity.ok().body(service.getByStudyYear(year).stream().map(mapper::toGet).toList());
    }

    @GetMapping("/by-class-type/{typeId:\\d+}")
    @Operation(summary = "Список класів", description = "Список класів за типом класу")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання.",
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
    })
    public ResponseEntity<List<GetClass>> getByClassTypeId(@PathVariable String typeId) {
        return ResponseEntity.ok(service.getByClassTypeId(Integer.parseInt(typeId)).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Список класів", description = "Список класів")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetClass.class)
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @GetMapping
    public ResponseEntity<List<GetClass>> getAll() {
        return ResponseEntity.ok().body(service.getAll().stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Клас", description = "Клас за ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetClass.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @GetMapping("by-id/{id:\\d+}")
    public ResponseEntity<GetClass> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id).map(mapper::toGet).orElseThrow(ClassNotFoundException::new));
    }

    @Operation(summary = "Новий клас", description = "Створює клас")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успішне створює",
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "Посилання на ресурс",
                                    example = "http://localhost8080/classes/by-id/1"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Помилка даних",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateClass newClass, UriComponentsBuilder uriBuilder) {
        var created = service.create(newClass);
        return ResponseEntity
                .created(uriBuilder.replacePath("classes/by-id/{id}").build(created.getId()))
                .build();
    }

    @Operation(summary = "Видалення клас", description = "Видаляє клас")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успішне видалення"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Помилка даних",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
