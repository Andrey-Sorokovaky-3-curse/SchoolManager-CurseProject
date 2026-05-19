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
import pro.sorokovsky.schoolmanagerbackend.contract.employee.CreateEmployee;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.GetEmployee;
import pro.sorokovsky.schoolmanagerbackend.contract.passport.CreatePassport;
import pro.sorokovsky.schoolmanagerbackend.exception.employee.EmployeeNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.EmployeeMapper;
import pro.sorokovsky.schoolmanagerbackend.service.EmployeesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("employees")
@Tag(name = "Працівники")
public class EmployeesController {
    private final EmployeesService service;
    private final EmployeeMapper mapper;

    @GetMapping("by-position/{positionId:\\d+}")
    @Operation(summary = "Відділ кадрів", description = "Отримує працівників за посадою")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetEmployee.class)
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
    public ResponseEntity<List<GetEmployee>> findByPosition(@PathVariable Integer positionId) {
        return ResponseEntity.ok().body(service.getByPosition(positionId).stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Відділ кадрів", description = "Отримує працівників")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetEmployee.class)
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
    public ResponseEntity<List<GetEmployee>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toGet).toList());
    }

    @Operation(summary = "Працівник", description = "Отримує працівника за ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetEmployee.class)
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
                    description = "не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
    })
    @GetMapping("by-id/{id:\\d+}")
    public ResponseEntity<GetEmployee> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id).map(mapper::toGet).orElseThrow(EmployeeNotFoundException::new));
    }

    @Operation(summary = "Новий працівник", description = "Створює працівника")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Успішне створення",
                    headers = {
                            @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "Посилання на працівника",
                                    example = "http://localhost:8080/employees/by-id/1"
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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Працівник вже існує для користувача",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreateEmployee employee,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        final var created = service.create(employee);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("employees/by-id/{id}").buildAndExpand(created.getId())
                .toUri()).build();
    }

    @Operation(summary = "Створення паспорту", description = "Створює паспорт для працівника працівника")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне створення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetEmployee.class)
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
                    description = "не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    @PutMapping("add-passport/{id:\\d+}")
    public ResponseEntity<GetEmployee> addPassport(
            @PathVariable Integer id,
            @Valid @RequestBody CreatePassport passport
            ) {
        return ResponseEntity
                .ok(mapper.toGet(service.addPassport(id, passport)));
    }

    @PutMapping("remove-passport/{id:\\d+}/{passportId:\\d+}")
    @Operation(summary = "Видалення паспорту", description = "Видаляє паспорт для працівника працівника")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне видалення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetEmployee.class)
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
                    description = "не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    public ResponseEntity<GetEmployee> removePassport(@PathVariable Integer id, @PathVariable Integer passportId) {
        return ResponseEntity
                .ok(mapper.toGet(service.removePassport(id, passportId)));
    }

    @PutMapping("add-position/{id:\\d+}/{positionId:\\d+}")
    @Operation(summary = "Прикріплення посади", description = "Прикріплює посаду до користувача")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне прикріплення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetEmployee.class)
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
                    description = "не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    public ResponseEntity<GetEmployee> addPosition(@PathVariable Integer id, @PathVariable Integer positionId) {
        return ResponseEntity
                .ok(mapper.toGet(service.addPosition(id, positionId)));
    }

    @PutMapping("remove-position/{id:\\d+}/{positionId:\\d+}")
    @Operation(summary = "Відкріплення посади", description = "Відкріплює посади від користувача")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне відкріплення",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetEmployee.class)
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
                    description = "не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    public ResponseEntity<GetEmployee> removePosition(@PathVariable Integer id, @PathVariable Integer positionId) {
        return ResponseEntity
                .ok(mapper.toGet(service.removePosition(id, positionId)));
    }

    @DeleteMapping("{id:\\d+}")
    @Operation(summary = "Видалення працівника", description = "Видаляє працівника")
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
                    description = "не знайдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
