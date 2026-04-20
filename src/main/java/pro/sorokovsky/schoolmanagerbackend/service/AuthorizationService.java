package pro.sorokovsky.schoolmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import pro.sorokovsky.schoolmanagerbackend.contract.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.contract.LoginUser;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.model.User;

@RequiredArgsConstructor
@Service
public class AuthorizationService {
    private final UsersService usersService;

    public void register(CreateUser newUser) {
        final var createdUser = usersService.create(newUser);
        authorize(createdUser);
    }

    public void login(@NonNull LoginUser loginUser) {
        final var candidate = usersService.getByLogin(loginUser.login()).orElseThrow(UserNotFoundException::new);
        authorize(candidate);
    }

    public void logout() {

    }

    private void authorize(User user) {

    }
}
