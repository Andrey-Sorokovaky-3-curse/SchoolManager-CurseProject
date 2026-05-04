package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findById(Integer id);
    UserEntity save(UserEntity user);
}
