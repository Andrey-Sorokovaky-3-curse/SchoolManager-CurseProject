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
            )
    }
    )
    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody CreateEmployee employee,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        final var created = service.create(employee);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("employees/{id}").buildAndExpand(created.getId())
                .toUri()).build();
    }

    @Operation(summary = "Видалення працівника", description = "Видаляє працівника")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успішне створення"
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
    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
