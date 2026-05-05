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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sorokovsky.schoolmanagerbackend.contract.authorization.classes.GetClass;
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
}
