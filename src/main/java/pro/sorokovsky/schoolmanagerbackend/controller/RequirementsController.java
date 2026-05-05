package pro.sorokovsky.schoolmanagerbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sorokovsky.schoolmanagerbackend.contract.requirement.GetRequirement;
import pro.sorokovsky.schoolmanagerbackend.contract.user.GetUser;
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
}
