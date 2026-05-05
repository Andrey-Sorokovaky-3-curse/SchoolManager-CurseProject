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
import org.springframework.web.bind.annotation.RestController;
import pro.sorokovsky.schoolmanagerbackend.contract.classes.GetClass;
import pro.sorokovsky.schoolmanagerbackend.contract.pupil.GetPupil;
import pro.sorokovsky.schoolmanagerbackend.mapper.PupilMapper;
import pro.sorokovsky.schoolmanagerbackend.service.PupilsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Учні")
public class PupilsController {
    private final PupilsService service;
    private final PupilMapper mapper;

    @GetMapping("by-class/{classId:\\d+}")
    @Operation(summary = "Список учнів", description = "Список учнів за класом")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання.",
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
}
