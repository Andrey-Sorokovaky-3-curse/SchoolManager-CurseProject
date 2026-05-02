package pro.sorokovsky.schoolmanagerbackend.mapper;

import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.model.Responsibility;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ResponsibilityMapper implements RowMapper<Responsibility> {
    @Override
    public @Nullable Responsibility mapRow(ResultSet table, int rowNum) throws SQLException {
        return Responsibility
                .builder()
                .id(table.getLong("Id"))
                .name(table.getString("Name"))
                .description(table.getString("Description"))
                .build();
    }
}
