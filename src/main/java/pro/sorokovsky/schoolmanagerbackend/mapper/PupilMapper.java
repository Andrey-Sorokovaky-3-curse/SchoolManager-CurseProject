package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.contract.pupil.GetPupil;
import pro.sorokovsky.schoolmanagerbackend.entity.PupilEntity;

@Component
@RequiredArgsConstructor
public class PupilMapper {
    private final ParentMapper parentMapper;
    private final ClassMapper classMapper;

    public GetPupil toGet(PupilEntity entity) {
        return new GetPupil(
                entity.getId(),
                entity.getLogin(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getMiddleName(),
                entity.getBirthday(),
                entity.getGender(),
                entity.getAddress(),
                entity.getExtraInformation(),
                parentMapper.toGet(entity.getMother()),
                parentMapper.toGet(entity.getFather()),
                classMapper.toGet(entity.getClazz())
        );
    }
}
