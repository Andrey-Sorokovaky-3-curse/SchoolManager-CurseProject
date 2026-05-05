package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.classes.GetClass;
import pro.sorokovsky.schoolmanagerbackend.entity.ClassEntity;

@Component
@RequiredArgsConstructor
public class ClassMapper {
    private final EmployeeMapper employeeMapper;
    private final ClassTypeMapper classTypeMapper;

    public GetClass toGet(ClassEntity entity) {
        return new GetClass(
                entity.getId(),
                employeeMapper.toGet(entity.getCurator()),
                classTypeMapper.toGet(entity.getClassType()),
                entity.getLetter(),
                entity.getStudyYear(),
                entity.getCreatedAtYear(),
                entity.getPupilsCount()
        );
    }
}
