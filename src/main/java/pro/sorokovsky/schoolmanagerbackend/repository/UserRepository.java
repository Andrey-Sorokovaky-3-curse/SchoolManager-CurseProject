package pro.sorokovsky.schoolmanagerbackend.repository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserRowMapper;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;
import pro.sorokovsky.schoolmanagerbackend.model.User;
import pro.sorokovsky.schoolmanagerbackend.repository.sql.UserSql;

import java.sql.Statement;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    public Optional<User> findByLogin(String login) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(UserSql.SELECT_BY_LOGIN, rowMapper, login));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(UserSql.SELECT_BY_ID, rowMapper, id));
        } catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }

    public User create(User user) {
        try {
            final var keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                final var preparedStatement = connection.prepareStatement(UserSql.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getLastName());
                preparedStatement.setString(5, user.getMiddleName());
                preparedStatement.setObject(6, user.getBirthday());
                preparedStatement.setBoolean(7, user.getGender() == Gender.MALE);
                preparedStatement.setString(8, user.getAddress());
                return preparedStatement;
            }, keyHolder);
            final var newId = Optional.ofNullable(keyHolder.getKey())
                    .map(Number::longValue)
                    .orElseThrow(UserNotFoundException::new);
            return findById(newId).orElseThrow();
        } catch (DuplicateKeyException exception) {
            throw new UserAlreadyExistsException();
        }
    }

    public User update(@NonNull User user) {
        final var genderBit = user.getGender() == Gender.MALE;
        final var rowsAffected = jdbcTemplate.update(
                UserSql.UPDATE_SQL,
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getBirthday(),
                genderBit,
                user.getAddress(),
                user.getId()
        );
        if (rowsAffected > 0) {
            return findById(user.getId()).orElseThrow(UserNotFoundException::new);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void delete(@NonNull User user) {
        if (jdbcTemplate.update(UserSql.DELETE_SQL, user.getId()) == 0) {
            throw new UserNotFoundException();
        }
    }
}
