package pro.sorokovsky.schoolmanagerbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findById(Integer id);
    UserEntity save(UserEntity user);

    boolean existsByLogin(String login);

    List<UserEntity> findAll();

    @Query("SELECT user FROM UserEntity user WHERE user.role = USER")
    List<UserEntity> findAllUsers();
}
