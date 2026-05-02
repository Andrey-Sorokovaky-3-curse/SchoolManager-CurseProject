package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.model.User;
import pro.sorokovsky.schoolmanagerbackend.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User create(@NonNull CreateUser newUser) {
        final var user = User
                .builder()
                .login(newUser.login())
                .password(passwordEncoder.encode(newUser.password()))
                .firstName(newUser.firstName())
                .lastName(newUser.lastName())
                .middleName(newUser.middleName())
                .birthday(newUser.birthday())
                .gender(newUser.gender())
                .address(newUser.address())
                .build();
        return repository.create(user);
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<User> getByLogin(String login) {
        return repository.findByLogin(login);
    }

    public User update(User user) {
        return repository.update(user, user.getId());
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
