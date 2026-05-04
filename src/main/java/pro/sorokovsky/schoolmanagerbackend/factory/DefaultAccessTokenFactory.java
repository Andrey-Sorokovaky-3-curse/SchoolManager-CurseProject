package pro.sorokovsky.schoolmanagerbackend.factory;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import pro.sorokovsky.schoolmanagerbackend.entity.Token;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Builder
public class DefaultAccessTokenFactory implements AccessTokenFactory {
    private final Duration lifetime;

    @Override
    public Token apply(@NonNull Token token) {
        final var now = Instant.now();
        return new Token(
                token.id(),
                token.subject(),
                token.authorities(),
                now,
                now.plus(lifetime)
        );
    }
}
