package pro.sorokovsky.schoolmanagerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pro.sorokovsky.schoolmanagerbackend.contract.GetUser;

import java.sql.Date;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserModel implements UserDetails {
    private Integer id;
    private String login;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private Gender gender;
    private String address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new LinkedList<>(List.of(new SimpleGrantedAuthority("ROLE_%s".formatted(role))));
    }

    @Override
    public String getUsername() {
        return login;
    }
}
