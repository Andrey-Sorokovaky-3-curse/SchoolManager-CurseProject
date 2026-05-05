package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.user.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.contract.user.UpdateUser;
import pro.sorokovsky.schoolmanagerbackend.entity.Roles;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;
import pro.sorokovsky.schoolmanagerbackend.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(@NonNull CreateUser newUser) {
        return repository.save(UserEntity
                .builder()
                .login(newUser.login())
                .password(passwordEncoder.encode(newUser.password()))
                .role(Roles.USER.value())
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .middleName(newUser.middleName())
                .birthday(newUser.birthday().toLocalDate())
                .gender(newUser.gender())
                .address(newUser.address())
                .build());
    }

    public Optional<UserEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public Optional<UserEntity> getByLogin(String login) {
        return repository.findByLogin(login);
    }

    public UserEntity update(UserEntity user, UpdateUser updateUser) {
        if (updateUser.login() != null) user.setLogin(updateUser.login());
        if (updateUser.firstName() != null) user.setFirstName(updateUser.firstName());
        if (updateUser.lastName() != null) user.setLastName(updateUser.lastName());
        if (updateUser.middleName() != null) user.setMiddleName(updateUser.middleName());
        if (updateUser.birthday() != null) {
            user.setBirthday(updateUser.birthday().toLocalDate());
        }
        if (updateUser.gender() != null) user.setGender(updateUser.gender());
        if (updateUser.address() != null) user.setAddress(updateUser.address());
        return repository.save(user);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
