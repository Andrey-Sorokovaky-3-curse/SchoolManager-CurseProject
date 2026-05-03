package pro.sorokovsky.schoolmanagerbackend.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.exception.base.AlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.position.PositionAlreadyExistsException;
import pro.sorokovsky.schoolmanagerbackend.exception.position.PositionNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.position.RequirementAlreadyException;
import pro.sorokovsky.schoolmanagerbackend.exception.position.ResponsibilityAlreadyException;
import pro.sorokovsky.schoolmanagerbackend.mapper.PositionRowMapper;
import pro.sorokovsky.schoolmanagerbackend.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class PositionsRepository extends CrudRepository<Position> {
    private final JdbcTemplate jdbcTemplate;
    private final PositionRowMapper mapper;

    @Transactional
    public Position addResponsibility(Long id, Long responsibilityId) {
        final var sql = """
                INSERT INTO PositionsResponsibilities(PositionId, ResponsibilityId) VALUES (?, ?);
                """;
        try {
            jdbcTemplate.update(sql, id, responsibilityId);
            return findById(id).orElseThrow(PositionNotFoundException::new);
        } catch (DuplicateKeyException exception) {
            throw new ResponsibilityAlreadyException(exception);
        }
    }

    @Transactional
    public Position addRequirement(Long id, Long requirementId) {
        final var sql = """
                INSERT INTO PositionsRequirements(PositionId, RequirementId) VALUES (?, ?);
                """;
        try {
            jdbcTemplate.update(sql, id, requirementId);
            return findById(id).orElseThrow(PositionNotFoundException::new);
        } catch (DuplicateKeyException exception) {
            throw new RequirementAlreadyException(exception);
        }
    }

    @Transactional
    public Position removeResponsibility(Long id, Long responsibilityId) {
        final var sql = """
                DELETE FROM PositionsResponsibilities
                WHERE PositionId = ? AND ResponsibilityId = ?;
        """;
        jdbcTemplate.update(sql, id, responsibilityId);
        return findById(id).orElseThrow(PositionNotFoundException::new);
    }

    @Transactional
    public Position removeRequirement(Long id, Long requirementId) {
        final var sql = """
                DELETE FROM PositionsResponsibilities
                WHERE PositionId = ? AND ResponsibilityId = ?;
        """;
        jdbcTemplate.update(sql, id, requirementId);
        return findById(id).orElseThrow(PositionNotFoundException::new);
    }

    @Override
    protected String getByIdSql() {
        return """
                SELECT
                    p.Id AS PositionId,
                    p.Name AS PositionName,
                    p.Salary,
                
                    ISNULL((
                        SELECT
                            r.Id,
                            r.Name,
                            r.Description
                        FROM PositionsRequirements pr
                        INNER JOIN Requirements r ON r.Id = pr.RequirementId
                        WHERE pr.PositionId = p.Id
                        ORDER BY r.Name
                        FOR JSON PATH
                    ), '[]') AS Requirements,
                
                    ISNULL((
                        SELECT
                            res.Id,
                            res.Name,
                            res.Description
                        FROM PositionsResponsibilities pres
                        INNER JOIN Responsibilities res ON res.Id = pres.ResponsibilityId
                        WHERE pres.PositionId = p.Id
                        ORDER BY res.Name
                        FOR JSON PATH
                    ), '[]') AS Responsibilities
                
                FROM Positions p
                ORDER BY p.Name;""";
    }

    @Override
    protected RowMapper<Position> mapper() {
        return mapper;
    }

    @Override
    protected JdbcTemplate jdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    protected PreparedStatement prepareCreatingStatement(Connection connection, Position position) throws SQLException {
        final var statement = connection.prepareStatement("INSERT INTO Positions(Name, Salary) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, position.getName());
        statement.setDouble(2, position.getSalary());
        return statement;
    }

    @Override
    protected PreparedStatement prepareUpdatingStatement(Connection connection, Position position) throws SQLException {
        final var statement = connection.prepareStatement("UPDATE Positions SET Name=?, Salary=? WHERE Id=?");
        statement.setString(1, position.getName());
        statement.setDouble(2, position.getSalary());
        statement.setLong(3, position.getId());
        return statement;
    }

    @Override
    protected NotFoundException notFoundException() {
        return new PositionNotFoundException();
    }

    @Override
    protected AlreadyExistsException alreadyExistsException() {
        return new PositionAlreadyExistsException();
    }

    @Override
    protected String deleteByIdSql() {
        return "DELETE FROM Positions WHERE Id = ?";
    }
}
