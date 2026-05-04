package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.mapper.UserMapper;
import pro.sorokovsky.schoolmanagerbackend.model.Roles;
import pro.sorokovsky.schoolmanagerbackend.model.UserModel;
import pro.sorokovsky.schoolmanagerbackend.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    public UserModel create(@NonNull CreateUser newUser) {
        final var user = UserModel
                .builder()
                .login(newUser.login())
                .password(passwordEncoder.encode(newUser.password()))
                .role(Roles.USER.value())
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .middleName(newUser.middleName())
                .birthday(newUser.birthday())
                .gender(newUser.gender())
                .address(newUser.address())
                .build();
        return mapper.toModel(repository.save(mapper.toEntity(user)));
    }

    public Optional<UserModel> getById(Integer id) {
        return repository.findById(id).map(mapper::toModel);
    }

    public Optional<UserModel> getByLogin(String login) {
        return repository.findByLogin(login).map(mapper::toModel);
    }

    public UserModel update(UserModel user) {
        return mapper.toModel(repository.save(mapper.toEntity(user)));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .map(mapper::toModel)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
