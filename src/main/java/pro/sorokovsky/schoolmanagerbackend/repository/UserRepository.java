package pro.sorokovsky.schoolmanagerbackend.repository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserRowMapper;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;
import pro.sorokovsky.schoolmanagerbackend.model.User;

import java.sql.Statement;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    public Optional<User> findByLogin(String login) {
        final var sql = "SELECT Id, Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address " +
                "FROM Users WHERE Login = ?";
        return jdbcTemplate.query(sql, rowMapper, login)
                .stream()
                .findFirst();
    }

    public Optional<User> findById(Long id) {
        final var sql = "SELECT Id, Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address " +
                "FROM Users WHERE Id = ?";
        return jdbcTemplate.query(sql, rowMapper, id)
                .stream()
                .findFirst();
    }

    public User create(User user) {
        final var sql = "INSERT INTO Users(Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        final var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            final var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
                .orElseThrow(() -> new RuntimeException("User not saved"));
        return findById(newId).orElseThrow();
    }

    public User update(User user) {
        final var sql = "UPDATE Users SET Login = ?, " +
                "Password = ?, " +
                "FirstName = ?, " +
                "LastName = ?, " +
                "MiddleName = ?, " +
                "Birthday = ?, " +
                "Gender = ?, " +
                "Address = ? " +
                "WHERE Id = ?";
        final var genderBit = user.getGender() == Gender.MALE;
        final var rowsAffected = jdbcTemplate.update(
                sql,
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                genderBit,
                user.getAddress(),
                user.getId()
        );
        if (rowsAffected > 0) {
            return findById(user.getId()).orElseThrow();
        } else {
            throw new RuntimeException("User not saved");
        }
    }

    public void delete(User user) {
        final var sql = "DELETE FROM Users WHERE Id = ?";
        jdbcTemplate.update(sql, user.getId());
    }
}
