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
import pro.sorokovsky.schoolmanagerbackend.contract.position.CreatePosition;
import pro.sorokovsky.schoolmanagerbackend.contract.position.GetPosition;
import pro.sorokovsky.schoolmanagerbackend.contract.position.UpdatePosition;
import pro.sorokovsky.schoolmanagerbackend.exception.position.PositionNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.PositionMapper;
import pro.sorokovsky.schoolmanagerbackend.service.PositionsService;

import java.util.List;

@RestController
@RequestMapping("positions")
@Tag(name = "Посади")
@RequiredArgsConstructor
public class PositionsController {
    private final PositionsService service;
    private final PositionMapper mapper;

    @GetMapping("by-id/{id:\\d+}")
    @Operation(summary = "Конкретна посада", description = "Отримує посаду за ідинтифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPosition.class)
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
    })
    public ResponseEntity<GetPosition> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id).map(mapper::toGet).orElseThrow(PositionNotFoundException::new));
    }

    @GetMapping("by-term/{term}")
    @Operation(summary = "Посади", description = "Отримує посади за пошуком")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GetPosition.class))
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
    })
    public ResponseEntity<List<GetPosition>> getByTerm(@PathVariable String term) {
        return ResponseEntity.ok(service.getByTerm(term).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Створення посади", description = "Створює посаду")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне створення",
                    responseCode = "201",
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "Посилання на створену посаду",
                                    example = "http://localhost:8080/positions/by-id/1"
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
    })
    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreatePosition position,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        final var created = service.create(position);
        return ResponseEntity
                .created(uriComponentsBuilder.replacePath("positions/by-id/{id}").build(created.getId()))
                .build();
    }

    @Operation(summary = "Оновлення посади", description = "Оновлює посаду")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне оновлення",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPosition.class)
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
    })
    @PutMapping("{id:\\d+}")
    public ResponseEntity<GetPosition> update(@PathVariable Integer id, @Valid @RequestBody UpdatePosition position) {
        return ResponseEntity
                .ok(mapper.toGet(service.update(id, position)));
    }

    @Operation(summary = "Додавання вимоги", description = "Додає вимогу за ідентифікаторами")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне оновлення",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPosition.class)
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
                    description = "Вимога вже є у відповідальності",
                    responseCode = "409",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PutMapping("add-requirement/{id:\\d+}/{requirementId:\\d+}")
    public ResponseEntity<GetPosition> addRequirement(@PathVariable Integer id, @PathVariable Integer requirementId) {
        return ResponseEntity
                .ok(mapper.toGet(service.addRequirement(id, requirementId)));
    }

    @Operation(summary = "Видалення вимоги", description = "Видаляє вимогу за ідентифікаторами")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне оновлення",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPosition.class)
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
    })
    @PutMapping("remove-requirement/{id:\\d+}/{requirementId:\\d+}")
    public ResponseEntity<GetPosition> removeRequirement(@PathVariable Integer id, @PathVariable Integer requirementId) {
        return ResponseEntity
                .ok(mapper.toGet(service.removeRequirement(id, requirementId)));
    }

    @Operation(summary = "Додавання відповідальності", description = "Додає відповідальність за ідентифікаторами")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне оновлення",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPosition.class)
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
                    description = "Відповідальність вже є у посаді",
                    responseCode = "409",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @PutMapping("add-responsibility/{id:\\d+}/{responsibilityId:\\d+}")
    public ResponseEntity<GetPosition> addResponsibility(@PathVariable Integer id, @PathVariable Integer responsibilityId) {
        return ResponseEntity
                .ok(mapper.toGet(service.addResponsibility(id, responsibilityId)));
    }

    @Operation(summary = "Видалення відповідальності", description = "Видаляє відповідальність за ідентифікаторами")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне оновлення",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPosition.class)
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
    })
    @PutMapping("remove-responsibility/{id:\\d+}/{responsibilityId:\\d+}")
    public ResponseEntity<GetPosition> removeResponsibility(@PathVariable Integer id, @PathVariable Integer responsibilityId) {
        return ResponseEntity
                .ok(mapper.toGet(service.removeResponsibility(id, responsibilityId)));
    }

    @Operation(summary = "Видалення посади", description = "Видаляє посаду")
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
    })
    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
