package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.employee.GetEmployee;
import pro.sorokovsky.schoolmanagerbackend.entity.EmployeeEntity;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    private final PositionMapper positionMapper;
    private final PassportMapper passportMapper;

    public GetEmployee toGet(EmployeeEntity entity) {
        return new GetEmployee(
                entity.getId(),
                entity.getLogin(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMiddleName(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getAddress(),
                entity.getPhoneNumber(),
                entity.getPositions().stream().map(positionMapper::toGet).toList(),
                entity.getPassports().stream().map(passportMapper::toGet).toList()
        );
    }
}
