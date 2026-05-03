package pro.sorokovsky.schoolmanagerbackend.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.model.Position;
import pro.sorokovsky.schoolmanagerbackend.model.Requirement;
import pro.sorokovsky.schoolmanagerbackend.model.Responsibility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PositionRowMapper implements RowMapper<Position> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionRowMapper.class);

    @Override
    public @Nullable Position mapRow(ResultSet table, int rowNum) throws SQLException {
        return Position
                .builder()
                .id(table.getLong("Id"))
                .name(table.getString("Name"))
                .salary(table.getDouble("Salary"))
                .requirements(parseRequirements(table.getString("Requirements")))
                .responsibilities(parseResponsibilities(table.getString("Responsibilities")))
                .build();
    }

    private List<Requirement> parseRequirements(String json) {
        return parseJsonArray(json, new TypeReference<List<Requirement>>() {});
    }

    private List<Responsibility> parseResponsibilities(String json) {
        return parseJsonArray(json, new TypeReference<List<Responsibility>>() {});
    }

    private <T> List<T> parseJsonArray(String json, TypeReference<List<T>> typeReference) {
        if (json == null || json.isBlank() || "[]".equals(json.trim())) {
            return List.of();
        }
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
            return List.of();
        }
    }
}
