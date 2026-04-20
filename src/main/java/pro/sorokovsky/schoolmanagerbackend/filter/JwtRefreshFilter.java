package pro.sorokovsky.schoolmanagerbackend.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;
import pro.sorokovsky.schoolmanagerbackend.factory.AccessTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.factory.RefreshTokenFactory;
import pro.sorokovsky.schoolmanagerbackend.service.UsersService;
import pro.sorokovsky.schoolmanagerbackend.storage.TokenStorage;

import java.io.IOException;

@RequiredArgsConstructor
@Builder
public class JwtRefreshFilter extends OncePerRequestFilter {
    private final TokenStorage refreshTokenStorage;
    private final TokenStorage accessTokenStorage;
    private final AccessTokenFactory accessTokenFactory;
    private final RefreshTokenFactory refreshTokenFactory;
    private final UsersService usersService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    )
            throws ServletException, IOException {
        final var refreshToken = refreshTokenStorage.getToken(request).orElse(null);
        final var accessToken = accessTokenStorage.getToken(request).orElse(null);
        if (accessToken == null && refreshToken != null) {
            final var user = usersService.getByLogin(refreshToken.login()).orElse(null);
            if (user != null) {
                final var newRefreshToken = refreshTokenFactory.apply(user);
                final var newAccessToken = accessTokenFactory.apply(newRefreshToken);
                refreshTokenStorage.setToken(newRefreshToken, response);
                accessTokenStorage.setToken(newAccessToken, response);
            }
        }
        filterChain.doFilter(request, response);
    }
}
