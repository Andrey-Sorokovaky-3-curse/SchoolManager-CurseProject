package pro.sorokovsky.schoolmanagerbackend.storage;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import pro.sorokovsky.schoolmanagerbackend.deserializer.TokenDeserializer;
import pro.sorokovsky.schoolmanagerbackend.model.Token;
import pro.sorokovsky.schoolmanagerbackend.serialization.TokenSerializer;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Builder
public class CookieTokenStorage implements TokenStorage {
    private final TokenSerializer serializer;
    private final TokenDeserializer deserializer;
    private final String cookieName;

    @Override
    public Optional<Token> getToken(@NonNull HttpServletRequest request) {
        if (request.getCookies() == null) return Optional.empty();
        return Stream.of(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst()
                .map(Cookie::getValue)
                .flatMap(deserializer);
    }

    @Override
    public void setToken(Token token, HttpServletResponse response) {
        final var stringToken = serializer.apply(token).orElse(null);
        if (stringToken == null) return;
        final var maxAge = (int) ChronoUnit.SECONDS.between(token.createdAt(), token.expiresAt());
        response.addCookie(generateCookie(cookieName, stringToken, maxAge));
    }

    @Override
    public void clearToken(@NonNull HttpServletResponse response) {
        response.addCookie(generateCookie(cookieName, "", 0));
    }

    private static @NonNull Cookie generateCookie(String name, String value, int maxAge) {
        final var cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setDomain(null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }
}
