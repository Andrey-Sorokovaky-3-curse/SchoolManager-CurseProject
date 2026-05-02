package pro.sorokovsky.schoolmanagerbackend.repository.sql;

import org.jspecify.annotations.NonNull;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;
import pro.sorokovsky.schoolmanagerbackend.exception.base.ConflictException;
import pro.sorokovsky.schoolmanagerbackend.exception.base.NotFoundException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public abstract class CrudRepository<Model> {
    @Transactional(readOnly = true)
    public Optional<Model> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate().queryForObject(getByIdSql(), mapper(), id));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Transactional
    public Model create(Model model) {
        try {
            final var keyHolder = new GeneratedKeyHolder();
            jdbcTemplate().update(connection -> prepareCreatingStatement(connection, model), keyHolder);
            final var newId = Optional.ofNullable(keyHolder.getKey())
                    .map(Number::longValue)
                    .orElseThrow(this::notFoundException);
            return findById(newId).orElseThrow(this::notFoundException);
        } catch (DuplicateKeyException exception) {
            throw alreadyExistsException();
        }
    }

    @Transactional
    public Model update(@NonNull Model model, Long id) {
        jdbcTemplate().update(connection -> prepareUpdatingStatement(connection, model));
        return findById(id).orElseThrow(this::notFoundException);
    }

    @Transactional
    public void delete(@NonNull Long id) {
        if (jdbcTemplate().update(deleteByIdSql(), id) == 0) {
            throw notFoundException();
        }
    }

    protected abstract String getByIdSql();

    protected abstract RowMapper<Model> mapper();

    protected abstract JdbcTemplate jdbcTemplate();

    protected abstract PreparedStatement prepareCreatingStatement(Connection connection, Model model) throws SQLException;

    protected abstract PreparedStatement prepareUpdatingStatement(Connection connection, Model model) throws SQLException;

    protected abstract NotFoundException notFoundException();
    protected abstract ConflictException alreadyExistsException();

    protected abstract String deleteByIdSql();
}
