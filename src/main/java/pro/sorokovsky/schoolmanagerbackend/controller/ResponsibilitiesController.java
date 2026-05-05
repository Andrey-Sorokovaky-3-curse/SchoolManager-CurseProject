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
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.GetRequirement;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.CreateResponsibility;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.GetResponsibility;
import pro.sorokovsky.schoolmanagerbackend.contract.responsibility.UpdateResponsibility;
import pro.sorokovsky.schoolmanagerbackend.exception.responsibility.ResponsibilityNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.ResponsibilityMapper;
import pro.sorokovsky.schoolmanagerbackend.service.ResponsibilitiesService;

import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Відповідальності")
@RestController
@RequestMapping("responsibilities")
public class ResponsibilitiesController {
    private final ResponsibilitiesService service;
    private final ResponsibilityMapper mapper;

    @GetMapping("by-id{id:\\d+}")
    @Operation(summary = "Одна відповідальність", description = "Отримати відповідальність за ідинтифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetResponsibility.class)
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
                    description = "Відповідальність не знайдена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity<GetResponsibility> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id).map(mapper::toGet).orElseThrow(ResponsibilityNotFoundException::new));
    }

    @Operation(summary = "Пошук відповідальності", description = "Шукає відповідальності по всім полям")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetResponsibility.class)
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
    public ResponseEntity<List<GetResponsibility>> search(@RequestParam String term) {
        return ResponseEntity.ok(service.search(term).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Нова відповідальність", description = "Створює відповідальність")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успішне створення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetResponsibility.class)
                    ),
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "Посилання на створену відповідальність",
                                    example = "https://localhost/responsibilities/1"
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
            @RequestBody @Valid CreateResponsibility responsibility,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        final var created = service.create(responsibility);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/responsibilities/{id}").build(created.getId()))
                .build();
    }

    @Operation(summary = "Оновлення відповідальності", description = "Оновлює відповідальність")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне оновлення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetResponsibility.class)
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
                    description = "Відповідальність не знайдена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @PutMapping("{id:\\d+}")
    public ResponseEntity<GetResponsibility> update(
            @PathVariable Integer id, @Valid @RequestBody UpdateResponsibility responsibility) {
        return ResponseEntity.ok(mapper.toGet(service.update(id, responsibility)));
    }

    @Operation(summary = "Видалення відповідальності", description = "Видаляє відповідальність")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успішне видалення",
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
                    description = "Відповідальність не знайдена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
