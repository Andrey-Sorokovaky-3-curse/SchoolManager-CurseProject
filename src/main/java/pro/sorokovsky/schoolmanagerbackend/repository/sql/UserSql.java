package pro.sorokovsky.schoolmanagerbackend.repository.sql;

public class UserSql {
    private UserSql() {}

    public static final String SELECT_FIELDS =
            "Id, Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address";
    public static final String INSERT_FIELDS =
            "Login, Password, FirstName, LastName, MiddleName, Birthday, Gender, Address";
    public static final String SELECT_BASE = "SELECT %s FROM Users".formatted(SELECT_FIELDS);
    public static final String SELECT_BY_ID = "%s WHERE Id = ?".formatted(SELECT_BASE);
    public static final String SELECT_BY_LOGIN = "%s WHERE Login = ?".formatted(SELECT_BASE);
    public static final String INSERT_USER = "INSERT INTO Users(%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            .formatted(INSERT_FIELDS);
    public static final String UPDATE_SQL =
            "UPDATE Users SET Login=?, Password=?, FirstName=?, LastName=?, " +
                    "MiddleName=?, Birthday=?, Gender=?, Address=? WHERE Id=?";
    public static final String DELETE_SQL = "DELETE FROM Users WHERE Id=?";
}
