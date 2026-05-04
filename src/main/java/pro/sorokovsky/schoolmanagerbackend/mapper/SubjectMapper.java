package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.subject.GetSubject;
import pro.sorokovsky.schoolmanagerbackend.entity.SubjectEntity;

@Component
@RequiredArgsConstructor
public class SubjectMapper {
    private final EmployeeMapper employeeMapper;

    public GetSubject toGet(SubjectEntity entity) {
        return new GetSubject(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                employeeMapper.toGet(entity.getTeacher())
        );
    }
}
