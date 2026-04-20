package pro.sorokovsky.schoolmanagerbackend.storage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import pro.sorokovsky.schoolmanagerbackend.deserializer.TokenDeserializer;
import pro.sorokovsky.schoolmanagerbackend.model.Token;
import pro.sorokovsky.schoolmanagerbackend.serialization.TokenSerializer;

import java.util.Optional;

@RequiredArgsConstructor
@Builder
public class BearerTokenStorage implements TokenStorage {
    private static final String BEARER_PREFIX = "Bearer ";

    private final TokenSerializer serializer;
    private final TokenDeserializer deserializer;

    @Override
    public Optional<Token> getToken(@NonNull HttpServletRequest request) {
        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER_PREFIX)) return Optional.empty();
        return deserializer.apply(header.substring(BEARER_PREFIX.length()));
    }

    @Override
    public void setToken(Token token, HttpServletResponse response) {
        final var stringToken = serializer.apply(token).orElse(null);
        if (stringToken == null) return;
        response.setHeader(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + stringToken);
    }

    @Override
    public void clearToken(@NonNull HttpServletResponse response) {
        response.setHeader(HttpHeaders.AUTHORIZATION, "");
    }
}
