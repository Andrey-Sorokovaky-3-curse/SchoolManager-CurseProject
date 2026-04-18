package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.model.User;
import pro.sorokovsky.schoolmanagerbackend.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    public User create(@NonNull User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.create(user);
    }

    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<User> getByLogin(String login) {
        return repository.findByLogin(login);
    }

    public User update(User user) {
        return repository.update(user);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
