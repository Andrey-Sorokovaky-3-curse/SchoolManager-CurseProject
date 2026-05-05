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
import pro.sorokovsky.schoolmanagerbackend.contract.subject.GetSubject;
import pro.sorokovsky.schoolmanagerbackend.mapper.SubjectMapper;
import pro.sorokovsky.schoolmanagerbackend.service.SubjectsService;

import java.util.List;

@RestController
@RequestMapping("subjects")
@RequiredArgsConstructor
@Tag(name = "Предмети")
public class SubjectsController {
    private final SubjectsService service;
    private final SubjectMapper mapper;

    @GetMapping("by-teacher/{teacherId:\\d+}")
    @Operation(summary = "Предмети", description = "Отримує предмети за вчителем")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успішне отримання.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = GetSubject.class)
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
    public ResponseEntity<List<GetSubject>> getByTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(service.getByTeacher(teacherId).stream().map(mapper::toGet).toList());
    }
}
