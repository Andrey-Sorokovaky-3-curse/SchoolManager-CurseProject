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
import pro.sorokovsky.schoolmanagerbackend.contract.schedule.CreateSchedule;
import pro.sorokovsky.schoolmanagerbackend.contract.schedule.GetSchedule;
import pro.sorokovsky.schoolmanagerbackend.exception.schedule.ScheduleNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.ScheduleMapper;
import pro.sorokovsky.schoolmanagerbackend.service.SchedulesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("schedules")
@Tag(name = "Розклад")
public class SchedulesController {
    private final SchedulesService service;
    private final ScheduleMapper mapper;

    @GetMapping
    @Operation(summary = "Розклад", description = "Отримує весь розклад")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GetSchedule.class))
                    )
            ),
            @ApiResponse(
                    description = "Не авторизовано",
                    responseCode = "401",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    public ResponseEntity<List<GetSchedule>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toGet).toList());
    }

    @GetMapping("by-class/{classId:\\d+}")
    @Operation(summary = "Розклад", description = "Отримує весь розклад за класом")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = GetSchedule.class))
                    )
            ),
            @ApiResponse(
                    description = "Не авторизовано",
                    responseCode = "401",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    public ResponseEntity<List<GetSchedule>> getByClass(@PathVariable Integer classId) {
        return ResponseEntity.ok(service.getByClass(classId).stream().map(mapper::toGet).toList());
    }

    @GetMapping("by-id/{id:\\d+}")
    @Operation(summary = "Розклад", description = "Отримує за ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне отримання",
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetSchedule.class)
                    )
            ),
            @ApiResponse(
                    description = "Не авторизовано",
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
    public ResponseEntity<GetSchedule> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id).map(mapper::toGet).orElseThrow(ScheduleNotFoundException::new));
    }

    @PostMapping
    @Operation(summary = "Створення розкладу", description = "Створює розклад")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне створення",
                    responseCode = "201",
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "Посилання на ресурс",
                                    example = "http://localhost/schedules/by-id/1"
                            )
                    }
            ),
            @ApiResponse(
                    description = "Не авторизовано",
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
                    description = "Не можливо створити",
                    responseCode = "409",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    public ResponseEntity<Void> create(@Valid @RequestBody CreateSchedule schedule, UriComponentsBuilder uriBuilder) {
        var created = service.create(schedule);
        return ResponseEntity
                .created(uriBuilder.replacePath("schedules/by-id/{id}").build(created.getId()))
                .build();
    }

    @DeleteMapping("{id:\\d+}")
    @Operation(summary = "Видалення розкладу", description = "Видаляє розклад")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Успішне видалення",
                    responseCode = "204"
            ),
            @ApiResponse(
                    description = "Не авторизовано",
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
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
