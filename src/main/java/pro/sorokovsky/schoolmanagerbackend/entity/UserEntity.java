package pro.sorokovsky.schoolmanagerbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pro.sorokovsky.schoolmanagerbackend.converter.GenderConverter;
import pro.sorokovsky.schoolmanagerbackend.model.Gender;

import java.time.LocalDate;

@Entity
@Table(schema = "dbo", name = "Users")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Login", length = 50, nullable = false, unique = true)
    private String login;

    @Column(name = "Password", nullable = false, length = 300)
    private String password;

    @Column(name = "Role", nullable = false, length = 15)
    private String role;

    @Column(name = "FirstName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "LastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "MiddleName", length = 50, nullable = false)
    private String middleName;

    @Column(name = "Birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "Gender", nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "Address", nullable = false, length = 200)
    private String address;
}
