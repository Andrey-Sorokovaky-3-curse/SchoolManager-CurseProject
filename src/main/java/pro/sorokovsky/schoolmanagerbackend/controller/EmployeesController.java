package pro.sorokovsky.schoolmanagerbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.GetEmployee;
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

    @GetMapping("by-position")
    @Operation(summary = "Відділ кадрів", description = "Отримує працівників за назвою посади.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetEmployee.class)
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
    public ResponseEntity<List<GetEmployee>> findByPosition(@RequestParam String name) {
        return ResponseEntity.ok().body(service.getByPosition(name).stream().map(mapper::toGet).toList());
    }
}
