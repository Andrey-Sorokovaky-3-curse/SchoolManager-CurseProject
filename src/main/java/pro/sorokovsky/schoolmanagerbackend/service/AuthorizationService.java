package pro.sorokovsky.schoolmanagerbackend.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.sorokovsky.schoolmanagerbackend.contract.CreateUser;
import pro.sorokovsky.schoolmanagerbackend.contract.LoginUser;
import pro.sorokovsky.schoolmanagerbackend.exception.authorization.BadCredentialsException;
import pro.sorokovsky.schoolmanagerbackend.exception.user.UserNotFoundException;
import pro.sorokovsky.schoolmanagerbackend.factory.AccessTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.factory.RefreshTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.model.UserModel;
import pro.sorokovsky.schoolmanagerbackend.storage.TokenStorage;

@RequiredArgsConstructor
@Builder
public class AuthorizationService {
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenFactory accessTokenFactory;
    private final RefreshTokenFactory refreshTokenFactory;
    private final TokenStorage accessTokenStorage;
    private final TokenStorage refreshTokenStorage;

    public void register(CreateUser newUser, HttpServletResponse response) {
        final var createdUser = usersService.create(newUser);
        authorize(createdUser, response);
    }

    public void login(@NonNull LoginUser loginUser, HttpServletResponse response) {
        final var candidate = usersService.getByLogin(loginUser.login()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(loginUser.password(), candidate.getPassword())) {
            throw new BadCredentialsException();
        }
        authorize(candidate, response);
    }

    public void logout(HttpServletResponse response) {
        accessTokenStorage.clearToken(response);
        refreshTokenStorage.clearToken(response);
    }

    private void authorize(UserModel user, HttpServletResponse response) {
        final var refreshToken = refreshTokenFactory.apply(user);
        final var accessToken = accessTokenFactory.apply(refreshToken);
        accessTokenStorage.setToken(accessToken, response);
        refreshTokenStorage.setToken(refreshToken, response);
    }
}
