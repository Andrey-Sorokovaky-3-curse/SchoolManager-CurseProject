package pro.sorokovsky.schoolmanagerbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
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
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.CreateRequirement;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.GetRequirement;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.UpdateRequirement;
import pro.sorokovsky.schoolmanagerbackend.exception.requirement.RequirementNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.RequirementMapper;
import pro.sorokovsky.schoolmanagerbackend.service.RequirementsService;

import java.util.List;

@RestController
@RequestMapping("requirements")
@Tag(name = "Вимоги")
@RequiredArgsConstructor
public class RequirementsController {
    private final RequirementsService service;
    private final RequirementMapper mapper;

    @GetMapping("by-id/{id:\\d+}")
    @Operation(summary = "Отримання вимоги", description = "Отримання вимоги за ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetRequirement.class)
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
                    description = "Вимога не знайдена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity<GetRequirement> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id).map(mapper::toGet).orElseThrow(RequirementNotFoundException::new));
    }

    @GetMapping
    public ResponseEntity<List<GetRequirement>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Пошук вимоги", description = "Шукає вимоги по всім полям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetRequirement.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @GetMapping("search")
    public ResponseEntity<List<GetRequirement>> search(@RequestParam String term) {
        return ResponseEntity.ok(service.search(term).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Нова вимога", description = "Створює вимогу")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успішне створення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetRequirement.class)
                    ),
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "Посилання на створену вимогу",
                                    example = "https://localhost/requirements/1"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неавторизований",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreateRequirement requirement,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        final var created = service.create(requirement);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/requirements/{id}").build(created.getId())).build();
    }

    @Operation(summary = "Оновлення вимоги", description = "Оновлює вимогу")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне оновлення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetRequirement.class)
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
                    responseCode = "400",
                    description = "Некоректні данні",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Вимога не знайдена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PutMapping("{id:\\d+}")
    public ResponseEntity<GetRequirement> update(
            @PathVariable Integer id, @Valid @RequestBody UpdateRequirement requirement
    ) {
        return ResponseEntity.ok(mapper.toGet(service.update(id, requirement)));
    }

    @Operation(summary = "Видалення вимоги", description = "Видаляє вимогу")
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
                    description = "Вимога не знайдена",
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
