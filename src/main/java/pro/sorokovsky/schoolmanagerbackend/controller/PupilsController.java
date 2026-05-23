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
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pro.sorokovsky.schoolmanagerbackend.contract.pupil.CreatePupil;
import pro.sorokovsky.schoolmanagerbackend.contract.pupil.GetPupil;
import pro.sorokovsky.schoolmanagerbackend.exception.pupil.PupilNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.PupilMapper;
import pro.sorokovsky.schoolmanagerbackend.service.PupilsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("pupils")
@Tag(name = "Учні")
public class PupilsController {
    private final PupilsService service;
    private final PupilMapper mapper;

    @GetMapping("by-class/{classId:\\d+}")
    @Operation(summary = "Список учнів", description = "Список учнів за класом")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetPupil.class)
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
    public ResponseEntity<List<GetPupil>> getByClassId(@PathVariable Integer classId) {
        return ResponseEntity.ok(service.getByClassId(classId).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Список учнів", description = "Список всіх учнів")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetPupil.class)
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
    @GetMapping
    public ResponseEntity<List<GetPupil>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Учень", description = "Отримує учня за ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPupil.class)
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
    public ResponseEntity<GetPupil> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.toGet(service.getById(id).orElseThrow(PupilNotFoundException::new)));
    }

    @Operation(summary = "Новий учень", description = "Створює учня")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успішне створення",
                    headers = {
                            @Header(
                                    name = MediaType.APPLICATION_JSON_VALUE,
                                    description = "Посилання на ресурс",
                                    example = "http://localhost:8080/pupils/by-id/1"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Невірні данні",
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
            @ApiResponse(
                    responseCode = "409",
                    description = "Вже існує",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreatePupil pupil,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var created = service.create(pupil);
        return ResponseEntity
                .created(uriComponentsBuilder.replacePath("pupils/by-id/{id}").build(created.getId()))
                .build();
    }

    @GetMapping("by-parent/{id}")
    public ResponseEntity<List<GetPupil>> getByParent(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getByParent(id).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Видалення учня", description = "Видаляє учня")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успішне видалення"
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
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
