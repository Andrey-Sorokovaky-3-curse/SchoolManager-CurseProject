package pro.sorokovsky.schoolmanagerbackend.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.responsibility.ResponsibilityAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.responsibility.ResponsibilityNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.ResponsibilityMapper;
import pro.sorokovsky.schoolmanagerbackend.model.Responsibility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class ResponsibilityRepository extends CrudRepository<Responsibility> {
    private final JdbcTemplate jdbcTemplate;
    private final ResponsibilityMapper mapper;

    @Override
    protected String getByIdSql() {
        return "SELECT Id, Name, Description FROM Responsibilities WHERE Id = ?";
    }

    @Override
    protected RowMapper<Responsibility> mapper() {
        return mapper;
    }

    @Override
    protected JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    protected PreparedStatement prepareCreatingStatement(Connection connection, Responsibility responsibility) throws SQLException {
        final var statement = connection.prepareStatement("INSERT INTO Responsibilities (Name, Description) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, responsibility.getName());
        statement.setString(2, responsibility.getDescription());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdatingStatement(Connection connection, Responsibility responsibility) throws SQLException {
        final var statement = connection.prepareStatement("UPDATE Responsibilities SET Name=?, Description=? WHERE Id=?");
        statement.setString(1, responsibility.getName());
        statement.setString(2, responsibility.getDescription());
        statement.setLong(3, responsibility.getId());
        return statement;
    }

    @Override
    protected NotFoundException notFoundException() {
        return new ResponsibilityNotFoundException();
    }

    @Override
    protected AlreadyExistsException alreadyExistsException() {
        return new ResponsibilityAlreadyExistsException();
    }

    @Override
    protected String deleteByIdSql() {
        return "DELETE FROM Responsibilities WHERE Id = ?";
    }
}
