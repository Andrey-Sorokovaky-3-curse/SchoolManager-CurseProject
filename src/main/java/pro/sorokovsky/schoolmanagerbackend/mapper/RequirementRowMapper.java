package pro.sorokovsky.schoolmanagerbackend.mapper;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.model.Requirement;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RequirementRowMapper implements RowMapper<Requirement> {
    @Override
    public @Nullable Requirement mapRow(@NonNull ResultSet table, int rowNum) throws SQLException {
        return Requirement
                .builder()
                .id(table.getLong("Id"))
                .name(table.getString("Name"))
                .description(table.getString("Description"))
                .build();
    }
}
