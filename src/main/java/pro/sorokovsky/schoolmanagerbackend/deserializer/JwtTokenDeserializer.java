package pro.sorokovsky.schoolmanagerbackend.deserializer;

import com.nimbusds.jwt.JWTClaimsSet;
import org.jspecify.annotations.NonNull;
import pro.sorokovsky.schoolmanagerbackend.model.Token;

import java.text.ParseException;
import java.util.UUID;

public abstract class JwtTokenDeserializer implements TokenDeserializer {
    protected Token extractTokenFromClaims(@NonNull JWTClaimsSet claims) throws ParseException {
        final var authorities = claims.getStringListClaim("authorities");
        return new Token(
                UUID.fromString(claims.getJWTID()),
                claims.getSubject(),
                authorities,
                claims.getIssueTime().toInstant(),
                claims.getExpirationTime().toInstant()
        );
    }
}
