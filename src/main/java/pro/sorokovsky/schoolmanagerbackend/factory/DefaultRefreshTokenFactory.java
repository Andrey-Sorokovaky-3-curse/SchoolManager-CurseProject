package pro.sorokovsky.schoolmanagerbackend.factory;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import pro.sorokovsky.schoolmanagerbackend.entity.Token;
import pro.sorokovsky.schoolmanagerbackend.entity.UserEntity;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
public class DefaultRefreshTokenFactory implements RefreshTokenFactory {
    private final Duration lifetime;

    @Override
    public Token apply(UserEntity user) {
        final var now = Instant.now();
        return new Token(
                UUID.randomUUID(),
                user.getLogin(),
                List.of(),
                now,
                now.plus(lifetime)
        );
    }
}
