package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.user.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.entity.Roles;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserMapper;
import pro.sorokovsky.schoolmanagerbackend.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserEntity create(@NonNull CreateUser newUser) {
        return UserEntity
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
                .build();
    }

    public Optional<UserEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public Optional<UserEntity> getByLogin(String login) {
        return repository.findByLogin(login);
    }

    public UserEntity update(UserEntity user) {
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
