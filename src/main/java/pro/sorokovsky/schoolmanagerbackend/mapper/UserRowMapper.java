package pro.sorokovsky.schoolmanagerbackend.mapper;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;
import pro.sorokovsky.schoolmanagerbackend.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@Component
@RequiredArgsConstructor
public class UserRowMapper implements RowMapper<User> {
    @Override
    public @Nullable User mapRow(@NonNull ResultSet table, int rowNum) throws SQLException {
        return User
                .builder()
                .id(table.getLong("Id"))
                .login(table.getString("Login"))
                .password(table.getString("Password"))
                .firstName(table.getString("FirstName"))
                .gender(Gender.fromValue(table.getBoolean("Gender")))
                .lastName(table.getString("LastName"))
                .middleName(table.getString("MiddleName"))
                .birthday(table.getDate("Birthday"))
                .address(table.getString("Address"))
                .authorities(new LinkedList<>())
                .build();
    }
}
