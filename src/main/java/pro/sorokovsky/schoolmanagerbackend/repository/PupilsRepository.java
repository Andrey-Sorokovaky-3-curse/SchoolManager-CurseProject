package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.PupilEntity;

import java.util.List;

@Repository
public interface PupilsRepository extends CrudRepository<PupilEntity, Integer> {

    @Override
    List<PupilEntity> findAll();

    @Query("SELECT p FROM PupilEntity p WHERE p.clazz.id = :classId")
    List<PupilEntity> findAllByClass(@Param("classId") Integer classId);

    @Query("SELECT pupil FROM PupilEntity pupil WHERE pupil.mother.id = :parentId OR pupil.father.id = :parentId")
    List<PupilEntity> findAllByParent(@Param("parentId") Integer parentId);
}
