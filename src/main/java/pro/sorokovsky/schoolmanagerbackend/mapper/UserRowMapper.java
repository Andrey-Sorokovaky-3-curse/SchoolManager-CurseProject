package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;
import pro.sorokovsky.schoolmanagerbackend.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class UserRowMapper implements RowMapper<User> {
    @Override
    public @Nullable User mapRow(ResultSet table, int rowNum) throws SQLException {
        return User
                .builder()
                .id(table.getLong("Id"))
                .login(table.getString("Login"))
                .password(table.getString("Password"))
                .firstName(table.getString("FirstName"))
                .gender(table.getBoolean("Gender") ? Gender.MALE : Gender.FEMALE)
                .lastName(table.getString("LastName"))
                .middleName(table.getString("MiddleName"))
                .birthday(table.getDate("birthday"))
                .address(table.getString("Address"))
                .build();
    }
}
