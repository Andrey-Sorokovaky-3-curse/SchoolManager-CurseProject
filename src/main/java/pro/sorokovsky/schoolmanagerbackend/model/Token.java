package pro.sorokovsky.schoolmanagerbackend.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Token(UUID id, String login, List<String> authorities, Instant createdAt, Instant expiresAt) {
}
