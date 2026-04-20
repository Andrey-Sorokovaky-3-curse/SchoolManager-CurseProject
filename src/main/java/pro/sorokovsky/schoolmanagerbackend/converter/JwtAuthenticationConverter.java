package pro.sorokovsky.schoolmanagerbackend.converter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import pro.sorokovsky.schoolmanagerbackend.storage.TokenStorage;

import java.util.stream.Stream;

@RequiredArgsConstructor
public class JwtAuthenticationConverter implements AuthenticationConverter {
    private final TokenStorage accessTokenStorage;
    private final TokenStorage refreshTokenStorage;

    @Override
    public Authentication convert(HttpServletRequest request) {
        final var accessToken = accessTokenStorage.getToken(request).orElse(null);
        final var refreshToken = refreshTokenStorage.getToken(request).orElse(null);
        if (accessToken == null) return null;
        var authorities = accessToken.authorities().stream().map(SimpleGrantedAuthority::new).toList();
        if (refreshToken != null) {
            authorities = Stream.concat(
                    authorities.stream(),
                    refreshToken.authorities().stream().map(SimpleGrantedAuthority::new)
            ).toList();
        }
        return new PreAuthenticatedAuthenticationToken(accessToken, accessToken, authorities);
    }
}
