package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private Gender gender;
    private String address;
}
