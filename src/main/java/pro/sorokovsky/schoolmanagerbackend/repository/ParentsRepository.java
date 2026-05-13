package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.ParentEntity;

import java.util.List;

@Repository
public interface ParentsRepository extends CrudRepository<ParentEntity, Integer> {
    @Override
    List<ParentEntity> findAll();

    @Query(value = """
            SELECT DISTINCT parent FROM ParentEntity parent
            WHERE parent.id IN (SELECT pupil.father.id FROM PupilEntity pupil WHERE pupil.id = :pupilId)
            OR parent.id IN (SELECT pupil.mother.id FROM PupilEntity pupil WHERE pupil.id = :pupilId)
""")
    List<ParentEntity> findAllByPupil(@Param("pupilId") Integer pupilId);
}
