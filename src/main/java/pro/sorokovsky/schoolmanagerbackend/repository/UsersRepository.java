package pro.sorokovsky.schoolmanagerbackend.repository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserRowMapper;
import pro.sorokovsky.schoolmanagerbackend.model.User;
import pro.sorokovsky.schoolmanagerbackend.repository.sql.UserSql;

import java.sql.Statement;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepository {
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
            final var user = Optional.ofNullable(jdbcTemplate.queryForObject(UserSql.SELECT_BY_ID, rowMapper, id));
            return user.map(this::loadUser);
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
                preparedStatement.setBoolean(7, user.getGender().getValue());
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
        final var rowsAffected = jdbcTemplate.update(
                UserSql.UPDATE_SQL,
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getBirthday(),
                user.getGender(),
                user.getAddress(),
                user.getId()
        );
        if (rowsAffected > 0) {
            return findById(user.getId()).orElseThrow(UserNotFoundException::new);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void delete(Long id) {
        if (jdbcTemplate.update(UserSql.DELETE_SQL, id) == 0) {
            throw new UserNotFoundException();
        }
    }

    private User loadUser(User user) {
        final var role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .map(authority -> authority.replace("ROLE_", ""))
                .findFirst().orElse("User");
        String sql;
        switch (role) {
            case "Parent":
            {
                sql = "SELECT u.Login, p.PhoneNumber FROM Users u JOIN Parents p ON u.Id = p.UserId WHERE u.Id = ?;";
                break;
            }
            case "Pupil":
            {
                sql = "SELECT u.*, p.* FROM Users u JOIN Pupils p ON u.Id = p.UserId WHERE u.Id = ?;";
                break;
            }
            case "Employee": {
                sql = "SELECT u.*, e.* FROM Users u JOIN dbo.Employees e on u.Id = e.UserId WHERE u.Id = ?;";
                break;
            }
            default: {
                return user;
            }
        }
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }
}
