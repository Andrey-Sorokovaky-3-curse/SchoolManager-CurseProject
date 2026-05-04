package pro.sorokovsky.schoolmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
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
public class SubjectsController {
    private final SubjectsService service;
    private final SubjectMapper mapper;

    @GetMapping("by-teacher/{teacherId:\\d+}")
    public ResponseEntity<List<GetSubject>>  getByTeacher(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(service.getByTeacher(teacherId).stream().map(mapper::toGet).toList());
    }
}
