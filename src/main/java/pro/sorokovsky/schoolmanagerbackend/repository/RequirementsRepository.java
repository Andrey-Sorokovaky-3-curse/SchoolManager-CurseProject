package pro.sorokovsky.schoolmanagerbackend.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.requirement.RequirementAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.requirement.RequirementNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.mapper.RequirementRowMapper;
import pro.sorokovsky.schoolmanagerbackend.model.Requirement;
import pro.sorokovsky.schoolmanagerbackend.repository.sql.CrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class RequirementsRepository extends CrudRepository<Requirement> {
    private static final String CREATE_SQL = "INSERT INTO Requirements(Name, Description) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE Requirements SET Name=?, Description=? WHERE Id=?";

    private final JdbcTemplate jdbcTemplate;
    private final RequirementRowMapper mapper;

    @Override
    protected String getByIdSql() {
        return "SELECT Id, Name, Description FROM Requirements WHERE Id = ?";
    }

    @Override
    protected RowMapper<Requirement> mapper() {
        return mapper;
    }

    @Override
    protected JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    protected PreparedStatement prepareCreatingStatement(Connection connection, Requirement requirement) throws SQLException {
        final var statement = connection.prepareStatement(CREATE_SQL,  Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, requirement.getName());
        statement.setString(2, requirement.getDescription());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdatingStatement(Connection connection, Requirement requirement) throws SQLException {
        final var statement = connection.prepareStatement(UPDATE_SQL);
        statement.setString(1, requirement.getName());
        statement.setString(2, requirement.getDescription());
        statement.setLong(3, requirement.getId());
        return statement;
    }

    @Override
    protected NotFoundException notFoundException() {
        return new RequirementNotFoundException();
    }

    @Override
    protected AlreadyExistsException alreadyExistsException() {
        return new RequirementAlreadyExistsException();
    }

    @Override
    protected String deleteByIdSql() {
        return "DELETE FROM Requirements WHERE Id=?;";
    }
}
