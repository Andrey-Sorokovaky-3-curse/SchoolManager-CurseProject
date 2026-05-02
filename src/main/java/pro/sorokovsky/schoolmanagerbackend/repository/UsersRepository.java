package pro.sorokovsky.schoolmanagerbackend.repository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserRowMapper;
import pro.sorokovsky.schoolmanagerbackend.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersRepository extends CrudRepository<User> {
    private static final String SELECT_FIELDS =
            "Id, Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address";
    private static final String INSERT_FIELDS =
            "Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address";
    private static final String SELECT_BASE = "SELECT %s FROM Users".formatted(SELECT_FIELDS);
    private static final String SELECT_BY_ID = "%s WHERE Id = ?".formatted(SELECT_BASE);
    private static final String SELECT_BY_LOGIN = "%s WHERE Login = ?".formatted(SELECT_BASE);
    private static final String INSERT_USER = "INSERT INTO Users(%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            .formatted(INSERT_FIELDS);
    private static final String UPDATE_SQL =
            "UPDATE Users SET Login=?, Password=?, FirstName=?, LastName=?, " +
                    "MiddleName=?, Birthday=?, Gender=?, Address=? WHERE Id=?";
    private static final String DELETE_SQL = "DELETE FROM Users WHERE Id=?";
    
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    @Transactional(readOnly = true)
    public Optional<User> findByLogin(String login) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_LOGIN, rowMapper, login));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    protected String getByIdSql() {
        return SELECT_BY_ID;
    }

    @Override
    protected RowMapper<User> mapper() {
        return rowMapper;
    }

    @Override
    protected JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    protected PreparedStatement prepareCreatingStatement(Connection connection, User user) throws SQLException {
        final var statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        return fillupStatement(statement, user);
    }

    @Override
    protected PreparedStatement prepareUpdatingStatement(Connection connection, User user) throws SQLException {
        final var statement = fillupStatement(connection.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS), user);
        statement.setLong(9, user.getId());
        return statement;
    }

    @Override
    protected NotFoundException notFoundException() {
        return new UserNotFoundException();
    }

    @Override
    protected AlreadyExistsException alreadyExistsException() {
        return new UserAlreadyExistsException();
    }

    @Override
    protected String deleteByIdSql() {
        return DELETE_SQL;
    }

    private PreparedStatement fillupStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getMiddleName());
        statement.setObject(6, user.getBirthday());
        statement.setBoolean(7, user.getGender().getValue());
        statement.setString(8, user.getAddress());
        return statement;
    }
}
