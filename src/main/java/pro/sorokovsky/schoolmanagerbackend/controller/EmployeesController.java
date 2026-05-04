package pro.sorokovsky.schoolmanagerbackend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.GetEmployee;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;
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
    public ResponseEntity<List<GetEmployee>> findByPosition(@RequestParam String name) {
        return ResponseEntity.ok().body(service.getByPosition(name).stream().map(mapper::toGet).toList());
    }
}
